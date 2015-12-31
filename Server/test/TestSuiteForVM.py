'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-21 10 : 41
Description : Used to test local VM operations
            including creation, deletion, shutoff
            reboot, start and template;
'''
import sys
sys.path.append('/home/lhearen/Server')
from utils.OnceLogging import log, init
from time import sleep
from base import VM
import unittest
import xml.dom.minidom as minidom
from utils import libvirt

init("/var/log/xen/unittest.log", "DEBUG", log)

class VMTest(unittest.TestCase):
    def setUp(self):
        self.name = 'vm4test'
        pass
        try:
            self.__createVM()
            self.__startVM()
        except:
            pass

    def tearDown(self):
        pass
        # try:
            # self.__deleteVM()
        # except:
            # pass

    def __deleteVM(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 10 : 45
        Description : Remove the vm_name VM for later VM creation process.
        '''
        try:
            log.debug('Delete domain first')
            dom = VM.conn.lookupByName(self.name)
            if dom.isActive():
                dom.destroyFlags(int(0))
            dom.undefineFlags(int(0))
        except Exception, e:
            log.debug(str(e))

    def __createVM(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 10 : 49
        Description :
                    Make sure there is always a VM called 'test'
                    Delete the VM first if it exists, and then create
                    another VM called 'test'.
        '''
        _id = '103adb75-f41a-4dae-8bdc-c61e92c85e58'
        name = self.name
        memory = 1024
        vcpu = 2
        mac = ''
        diskDir = '/home/res/images/test.qcow2'
        isoDir = '/home/res/iso/CentOS-7.1.iso'
        bridgeSrc = 'ovs0'
        try:
            self.__deleteVM(self.name)
        except:
            pass
        log.debug("After deletion, now let's create a new one.")
        return VM.create(_id, name, memory, vcpu, mac, diskDir,
                         isoDir, bridgeSrc)

    def testCreate(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 10 : 59
        Description : Make sure the VM is created properly;
        '''
        dom = VM.conn.lookupByName(self.name)
        self.assertTrue(dom)

    def __shutoffVM(self, vm_name):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 01
        Description : If the VM is running, shut it off forcefully.
        '''
        try:
            dom = VM.conn.lookupByName(self.name)
            if dom.isActive():
                dom.destroyFlags(int(0))
        except Exception, e:
            log.debug(str(e))


    def __startVM(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 01
        Description : Start a VM by its name and meantime return its
                    domain object for further use.
        '''
        try:
            self.__shutoffVM(self.name)
        except:
            pass
        dom = VM.conn.lookupByName(self.name)
        uuidstr = dom.UUIDString()
        VM.start(uuidstr)
        return dom

    def __get_vm_id(self, name):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-22 14 : 15
        Description : Return the domain id of a VM by its name.
        '''
        id = -1
        try:
            dom = VM.conn.lookupByName(name)
            domain_element = minidom.parseString(dom.XMLDesc()).documentElement
            id = domain_element.getAttribute('id')
        except:
            pass
        return id

    def testStart(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 06
        Description : Make sure the starting operation takes effect
                    after at most 6 seconds.
        '''
        dom = self.__startVM()
        for i in range(3):
            sleep(2)
            if dom.isActive():
                break
        log.debug(dom.isActive())
        self.assertTrue(dom.isActive())

    def testShutdown(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 06
        Description : Make sure the shutdown operation can
                    take effect in certain time - 6s;
        '''
        dom = self.__startVM()
        VM.shutdown(dom.UUIDString())
        for i in range(3):
            sleep(2)
            if not dom.isActive():
                break
        self.assertFalse(dom.isActive())

    def testReboot(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 08
        Description : Detect two states changes from up to down
                    then from down to up.
        '''
        try:
            self.__startVM(self.name)
        except:
            pass
        dom_id0 = self.__get_vm_id(self.name)
        log.debug(dom_id0)
        _id = VM.conn.lookupByName(self.name).UUIDString()
        log.debug("reboot" + _id)
        VM.reboot(str(_id))
        dom_id1 = self.__get_vm_id(self.name)
        log.debug(dom_id1)
        self.assertTrue(dom_id1 > dom_id0)

    def testDelete(self):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-21 11 : 11
        Description : Make sure the deletion operation is effective;
        '''
        self.__deleteVM()
        try:
            VM.conn.lookupByName(self.name)
        except Exception, e:
            log.debug(e)
            log.debug(type(e))
        self.assertRaises(libvirt.libvirtError, VM.conn.lookupByName,
                          self.name)


if __name__ == "__main__":
    unittest.main()
