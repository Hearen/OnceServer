'''
Created on Oct 23, 2015

@author: lhearen
'''
import unittest
import libvirt
from bnlibvirt.BNLogging import log_unittest, init
from libvirt import libvirtError
import bnlibvirt.BNVMAPI as BNVMAPI

init("/var/log/xen/unittest.log", "DEBUG", log_unittest)
log = log_unittest

class NetworkTest(unittest.TestCase):

    def __get_libvirt_connection(self):
        libvirt_connection = None
        try:
            libvirt_connection = libvirt.open('xen:///')
        except Exception, exn:
            log.excepiton("Libivrt connect to xen:/// failed!")
            log.excepiton(exn)
        return libvirt_connection

    def setUp(self):
        self.session = "SessionForTest"
        self.bnvmapi =  BNVMAPI.instance()
        self.conn = self.__get_libvirt_connection()
        self.dom = self.conn.lookupByName('test')
        self.name = 'eth_test'
        self.bridge = 'ovs0'
        try:
            self.bnvmapi.VIF_create(self.session, self.name, self.bridge)
        except Exception:
            pass
        vm_uuidstr = self.dom.UUIDString()
        try:
            self.bnvmapi.VM_attach_vif(self.session, vm_uuidstr, self.name)
        except Exception:
            pass


    def tearDown(self):
        pass


    def testCreate(self):
        try:
            self.bnvmapi.VIF_destroy(self.session, self.name)
        except Exception:
            pass
            
        log.debug('trying to create a network')   
        ret = self.bnvmapi.VIF_create(self.session, self.name, self.bridge)
        self.assertEqual(ret['Status'], 'Success')
        
        self.assertEqual(ret['Value'], self.name)
    
    def __checkExistence(self, method, name):
        '''
        Via invoking a method to check whether the object's existence.
        '''
        existed = True
        try:
            method(name)
        except Exception:
            existed = False
            
        return existed

    def testDelete(self):
        self.bnvmapi.VIF_destroy(self.session, self.name)
        self.assertFalse(self.__checkExistence(self.conn.networkLookupByName, self.name))
        

    def testAttach(self):
        '''
        To attach successfully - VM  should be shutoff first.
        '''
        try:
            self.dom.destroy()
        except libvirtError:
            pass
        vm_uuidstr = self.dom.UUIDString()
        try:
            self.bnvmapi.VM_detach_vif(self.session, vm_uuidstr, self.name)
        except Exception:
            pass
        ret = self.bnvmapi.VM_attach_vif(self.session, vm_uuidstr, self.name)
        self.assertEqual(ret['Status'], 'Success', ret) 
    
    def testDetach(self):
        '''
        Detach can be used either in running or shutoff state 
        - but when it's running, the VM has to be rebooted to 
        truly detach the interface while the shutoff state will
        make the detaching operation take effect immediately.
        '''
        vm_uuidstr = self.dom.UUIDString()
        ret = self.bnvmapi.VM_detach_vif(self.session, vm_uuidstr, self.name)
        self.assertEqual(ret['Status'], 'Success', ret)
    
    def testRecord(self):
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()