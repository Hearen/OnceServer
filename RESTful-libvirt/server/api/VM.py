# from __future__ import print_function
import time
from Connection import Connection
from util.OnceLogging import log, init
from Utils import XMLConverter

init("/var/log/xen/libvirt.log", "DEBUG", log)

def create(uuid, name, memory, vcpu, diskDir, isoDir, bridgeSrc):
    '''
    coded by LHearen
    E-mail: LHearen@126.com
    Using limited parameters to create a VM and return its UUIDString;
    '''
    print("Inside create")
    if len(uuid) < 5:
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

def start(uuid, flags=0):
    '''
    commented by LHearen
    E-mail: LHearen@126.com
    @session is used by RPC
    @vm_ref is UUID
    @flags
    VIR_DOMAIN_NONE = 0
    VIR_DOMAIN_START_PAUSED = 1
    VIR_DOMAIN_START_AUTODESTROY = 2
    VIR_DOMAIN_START_BYPASS_CACHE = 4
    VIR_DOMAIN_START_FORCE_BOOT = 8
    VIR_DOMAIN_START_VALIDATE = 16
    '''
    conn = Connection.get_libvirt_connection()
    if conn:
        dom = conn.lookupByUUIDString(uuid)
        if dom:
            dom.createWithFlags(int(flags))
            return True
        else:
            log.error("Domain %s not found." % uuid)
    return False

def shutdown(uuid, flags=0):
    '''
    re-coded by LHearen
    E-mail: LHearen@126.com
    Make sure the shutdown operation is effective
    check its effectiveness and when after certain
    seconds, forcefully destroy it directly
    '''
    reserved_time = 3
    conn = Connection.get_libvirt_connection()
    if conn:
        dom = conn.lookupByUUIDString(uuid)
        if dom:
            dom.shutdownFlags(int(flags))
            while reserved_time > 0:
                time.sleep(1)
                if not dom.isActive():
                    return True
                reserved_time -= 1
            if dom.isActive():
                dom.destroyFlags(int(flags))
            return True
        else:
            log.error("Domain %s not found." % uuid)
    return False

def delete(uuid, flags=0):
    '''
    re-coded by LHearen
    E-mail: LHearen@126.com
    Delete all the resources related to the guest domain
    if it's running, then shut it down first directly,
    which may produce undesirable results, for example
    un-flushed disk cache in the guest
    '''
    conn = Connection.get_libvirt_connection()
    if conn:
        dom = conn.lookupByUUIDString(uuid)
        if dom:
            if dom.isActive():
                dom.destroyFlags(int(flags))
            dom.undefineFlags(int(flags))
            return True
        else:
            log.error("Domain %s not found." % uuid)
    return False


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

def reboot(uuid, flags=0):
    '''
    re-coded by LHearen
    E-mail: LHearen@126.com
    shutdown the guest domain first and then constantly
    check its status until it's dead and then start it
    '''
    conn = Connection.get_libvirt_connection()
    waited_time = 2
    if conn is None:
        return False
    try:
        dom = conn.lookupByUUIDString(uuid)
        dom.shutdownFlags(int(flags))
        for i in range(3):
            time.sleep(waited_time)
            if not dom.isActive():
                break
        if dom.isActive():
            dom.destroy()
        time.sleep(waited_time)
        if dom.isActive():
            return False

        try:
            dom.createWithFlags(flags)
        except Exception:
            return False
        else:
            return True
    except Exception:
        return False

