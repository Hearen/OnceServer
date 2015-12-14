from Connection import Connection
from util.OnceLogging import log, init
from Utils import XMLConverter

init("/var/log/xen/libvirt.log", "DEBUG", log)

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
    conn = Connection.get_libvirt_connection()
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
    return define_VM_by_xml(conn, xml_config)


def define_VM_by_xml(conn, xml_config):
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

