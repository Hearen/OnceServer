from utils.Connection import Connection
from utils.OnceLogging import log, init
from utils.Utils import XMLConverter

init("/var/log/xen/libvirt.log", "DEBUG", log)
conn = Connection.get_libvirt_connection()

def test():
    print "I'm in VM."

def create(_id, name, memory, vcpu, diskDir, isoDir, bridgeSrc):
    '''
    coded by LHearen
    E-mail: LHearen@126.com
    Using limited parameters to create a VM and return its UUIDString;
    '''
    uuid = _id
    print("Inside create")
    if len(uuid) < 5:
        uuid = None
    uuid = None
    hvm = {"loader": "/usr/lib/xen/boot/hvmloader"}
    hvm["boot"] = "cdrom"
    hvm["device_model"] = "/usr/lib64/xen/bin/qemu-system-i386"
    image = {"hvm": hvm}

    tap2 = {"dev": "hdc:cdrom"}
    tap2["uname"] = "tap:aio:" + isoDir
    tap2["mode"] = "r"

    vif = {"bridge": bridgeSrc}

    vbd = {"dev": "hda:disk"}
    vbd["uname"] = "tap:aio:" + diskDir
    vbd["mode"] = "r"

    vfb = {"location": "0.0.0.0:5900"}
    vfb["vnclisten"] = "0.0.0.0"

    console = {"location": "0"}
    xml_config = XMLConverter.toVMXml(uuid, name, memory, vcpu, image, tap2,
                                        vif, vbd, vfb, console)
    return define_VM_by_xml(xml_config)


def define_VM_by_xml(xml_config):
    '''
    added by LHearen
    E-mail: LHearen@126.com
    Used to define a VM by a xml configuration file, dump its
    automatically generated internal xml configuration file and
    initialize the extra configuration file for latter use.
    '''
    log.debug(xml_config)
    try:
        dom = conn.defineXML(xml_config)
    except Exception:
        return None
    return dom.UUIDString()


def start(_id, flags=0):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-15 15 : 44
    Description : Used to start the VM pointed by uuid;
    '''
    dom = conn.lookupByUUIDString(_id)
    try:
        dom.createWithFlags(int(flags))
    except Exception:
        log.error("Started dom %s failed" % _id)
        return False
    else:
        return True
