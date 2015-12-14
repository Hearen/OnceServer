import libvirt
from OnceLogging import log, init
class Connection():
    @staticmethod
    def get_libvirt_connection():
        '''
        Author: LHearen
        E-mail: LHearen@126.com
        Time  :	2015-12-14 15:51
        Description: Used to connect libvirt;
        '''
        init("/var/log/xen/libvirt.log", "DEBUG", log)
        libvirt_connection = None
        try:
            libvirt_connection = libvirt.open('xen:///')
        except Exception, e:
            log.exception("Libivrt connect to xen:/// failed!")
            log.exception(e)
        return libvirt_connection
