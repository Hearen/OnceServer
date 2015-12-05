import libvirt
from util.OnceLogging import log, init
class Connection():
    @staticmethod
    def get_libvirt_connection():
        init("/var/log/xen/libvirt.log", "DEBUG", log)
        libvirt_connection = None
        try:
            libvirt_connection = libvirt.open('xen:///')
        except Exception, e:
            log.exception("Libivrt connect to xen:/// failed!")
            log.exception(e)
        return libvirt_connection
