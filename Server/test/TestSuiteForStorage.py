'''
Created on Oct 22, 2015

@author: lhearen
'''
import unittest
import libvirt
from bnlibvirt import BNVMAPI
from libvirt import libvirtError
import xml.dom.minidom as minidom
from bnlibvirt.BNLogging import log_unittest, init
from bnlibvirt.BNVMAPI import xen_api_error
from xml.dom.expatbuilder import TEXT_NODE


init("/var/log/xen/unittest.log", "DEBUG", log_unittest)
log = log_unittest



class PoolTest(unittest.TestCase):
    def setUp(self):
        self.session = "SessionForTest"
        self.bnvmapi =  BNVMAPI.instance()
        self.conn = self.__get_libvirt_connection()
        self.pool_name = 'pool_test'
        self.target_path = '/home/test/tmp'

    def __get_libvirt_connection(self):
        libvirt_connection = None
        try:
            libvirt_connection = libvirt.open('xen:///')
        except Exception, exn:
            log.excepiton("Libivrt connect to xen:/// failed!")
            log.excepiton(exn)
        return libvirt_connection

    def tearDown(self):
        pass
    
    def testCreate(self):
        try:
            self.bnvmapi.SR_delete(self.session, self.pool_name)
        except Exception:
            pass
        pool_uuidstr = self.bnvmapi.SR_create(self.session, self.pool_name, self.target_path)['Value']
             
        self.assertTrue(pool_uuidstr, pool_uuidstr)
        
    def testListAll(self):
        names = self.bnvmapi.SR_list_all(self.session)['Value'].split(',')
        
        pools_count = len(self.conn.listStoragePools())
        
        self.assertEqual(len(names), pools_count, names.append(str(pools_count)))
        
        
    def testDelete(self):
        '''
        Delete here means you undefine a pool in which case you can no longer have access to it.
        '''
        self.bnvmapi.SR_delete(self.session, self.pool_name)
        
        pools_names = self.bnvmapi.SR_list_all(self.session)['Value'].split(',')
        self.assertFalse(self.pool_name in pools_names, pools_names)
        
        self.bnvmapi.SR_create(self.session, self.pool_name, self.target_path)
            

class VolumeTest(unittest.TestCase):
    def setUp(self):
        self.session = "SessionForTest"
        self.bnvmapi =  BNVMAPI.instance()
        self.conn = self.__get_libvirt_connection()
        self.pool_name = 'pool_test'
        self.vol_name = 'vol_test'
        self.des_vol_name = 'vol_test_ref'
        self.vol_size = 2
        self.target_path = '/home/test/tmp'
        self.pool = self.conn.storagePoolLookupByName(self.pool_name)
        self.dom = self.conn.lookupByName('test')
        self.target_disk = 'hdd'

    def __get_libvirt_connection(self):
        libvirt_connection = None
        try:
            libvirt_connection = libvirt.open('xen:///')
        except Exception, exn:
            log.excepiton("Libivrt connect to xen:/// failed!")
            log.excepiton(exn)
        return libvirt_connection

    def tearDown(self):
        pass


    def testCreate(self):
        try:
            volume = self.pool.storageVolLookupByName(self.vol_name)
            volume.delete()
        except Exception:
            pass
        
        self.bnvmapi.VDI_create(self.session, self.pool_name, self.vol_name, self.vol_size)
        self.assertTrue(self.pool.storageVolLookupByName(self.vol_name).name())
    
    def testListAll(self):
        vols_names = self.bnvmapi.VDI_list_all(self.session, self.pool_name)['Value'].split(',')
        
        volumes_count = len(self.pool.listAllVolumes())
#         log.debug(vols_names)
        self.assertEqual(len(vols_names), volumes_count, vols_names)

    def testDelete(self):
        try:
            self.bnvmapi.VDI_create(self.session, self.pool_name, self.vol_name, self.vol_size)
        except Exception:
            pass
        self.bnvmapi.VDI_delete(self.session, self.pool_name, self.vol_name)
        
        self.assertFalse(self.vol_name in [volume.name() for volume in self.pool.listAllVolumes()])
        
        self.bnvmapi.VDI_create(self.session, self.pool_name, self.vol_name, self.vol_size)
    
    def __get_pool_path(self, pool_name):
        pool_xml_config = self.conn.storagePoolLookupByName(pool_name).XMLDesc()
        
        domain_element = minidom.parseString(pool_xml_config).documentElement
        path_node = domain_element.getElementsByTagName('target')[0].getElementsByTagName('path')[0]
        
        ret = ''
        for node in path_node.childNodes:
            if node.nodeType == node.TEXT_NODE:
                ret += node.data
        return ret
    
    
    def testLink(self):
        ret = self.bnvmapi.VDI_clone_by_link(self.session, self.vol_name, self.des_vol_name, self.pool_name)
        status = ret['Status']
        self.assertEqual(status, 'Success', ret)
        
        des_name = self.pool.storageVolLookupByName(self.des_vol_name).name()
        self.assertTrue(des_name, '*********%s***********' % des_name)
        
        self.bnvmapi.VDI_delete(self.session, self.pool_name, self.des_vol_name)
    
    def testCopy(self):
        ret = self.bnvmapi.VDI_clone_by_copy(self.session, self.vol_name, self.pool_name, self.des_vol_name, self.pool_name)
        
        status = ret['Status']
        self.assertEqual(status, 'Success', ret)
        
        self.assertTrue(self.pool.storageVolLookupByName(self.des_vol_name).name())
        
        self.bnvmapi.VDI_delete(self.session, self.pool_name, self.des_vol_name)
    
    def __is_target_disk_existed(self, dom, target_disk):
        vm_xml = self.dom.XMLDesc()
        log.debug(vm_xml)
        domain_element = minidom.parseString(vm_xml).documentElement
        disk_nodes  = domain_element.getElementsByTagName('devices')[0].getElementsByTagName('disk')
        for node in disk_nodes:
            log.debug(node.getElementsByTagName('target')[0].getAttribute('dev') )
            if node.getElementsByTagName('target')[0].getAttribute('dev') == target_disk:
                return True
        return False
        
    
    def testAttach(self):
        if self.__is_target_disk_existed(self.dom, self.target_disk):
            self.bnvmapi.VM_detach_volume(self.session, self.dom.UUIDString(), self.target_disk)
            
        ret = self.bnvmapi.VM_attach_volume(self.session, self.dom.UUIDString(), self.pool_name, self.vol_name, self.target_disk)
        status = ret['Status']
        self.assertEqual(status, 'Success', ret)
        
        self.assertTrue(self.__is_target_disk_existed(self.dom, self.target_disk))
        
        
    def testDetach(self):
        if not self.__is_target_disk_existed(self.dom, self.target_disk):
            self.bnvmapi.VM_attach_volume(self.session, self.dom.UUIDString(), self.pool_name, self.vol_name, self.target_disk)
        ret = self.bnvmapi.VM_detach_volume(self.session, self.dom.UUIDString(), self.target_disk)
        status = ret['Status']
        self.assertEqual(status, 'Success', ret)
        
        self.assertFalse(self.__is_target_disk_existed(self.dom, self.target_disk))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()