import traceback
from utils.Connection import Connection
from utils.OnceLogging import log, init
from utils.XmlConverter import XmlConverter
from utils.DBHelper import VIFHelper
from utils.DBHelper import VMHelper
from utils.Tools import logNotFound

init("/var/log/xen/libvirt.log", "DEBUG", log)
conn = Connection.get_libvirt_connection()
VIFUUIDString = '27167fe7-fc9d-47d5-9cd0-717106ef67be'

def create(_id, name, source, macString):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-05 09:40
    Description : Used to create a virtual network card;
    '''
    if len(_id) < 5:
        from utils.UUIDGenerator import createString
        global VIFUUIDString
        _id = createString()
    name = name if name else _id
    VIFUUIDString = _id
    xmlConfig = XmlConverter.toNetXml(_id, name, source, macString)
    try:
        conn.networkDefineXML(xmlConfig)
    except Exception:
        log.exception(traceback.print_exc())
        return None
    return True

def delete(_id):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-05 10:23
    Description : Used to delete or undefine a virtual network card;
    '''
    try:
        vif = conn.networkLookupByUUIDString(_id)
        if vif.isActive():
            vif.destroy()
        vif.undefine()
        VIFHelper.remove({"_id": _id})
    except Exception, e:
        log.debug("VIF.delete Failed! Message: %s" % e)
        return None
    return True
def attach(vm_id, vif_id):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-06 15:35
    Description : Attaching a vif to a VM;
    '''
    dom = None
    try:
        dom = conn.lookupByUUIDString(vm_id)
    except Exception, e:
        logNotFound("VM", vm_id, e)
        return None
    ret = VIFHelper.retrieve({"_id": vif_id})
    macString = ret['macString']
    source = ret['source']
    interfaceXmlConfig = XmlConverter.toVIFXml(source, macString)
    try:
        dom.attachDeviceFlags(interfaceXmlConfig, 0)
        dataDict = {"busy": True, "attachedVM": vm_id}
        VIFHelper.update({"_id": vif_id}, dataDict)
        dataDict = {"vifs.vif_id": vif_id, "vifs.macString": macString}
        VMHelper.update({"_id": vm_id}, dataDict)
        return True
    except Exception, e:
        log.debug("Attaching VIF %s to VM %s failed! Message: %s" %
                  (vif_id, vm_id, e))
        return None


def detach(vm_id, vif_id):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-06 15:35
    Description : Detaching a vif from a VM;
    '''
    dom = None
    try:
        dom = conn.lookupByUUIDString(vm_id)
    except Exception, e:
        logNotFound("VM", vm_id, e)
        return None
    ret = VIFHelper.retrieve({"_id": vif_id})
    macString = ret['macString']
    source = ret['source']
    interfaceXmlConfig = XmlConverter.toVIFXml(source, macString)
    try:
        dom.detachDeviceFlags(interfaceXmlConfig, 0)
        dataDict = {"busy": False, "attachedVM": ""}
        VIFHelper.update({"_id": vif_id}, dataDict)
        dataDict = {"vifs.vif_id": "", "vifs.macString": ""}
        VMHelper.update({"_id": vm_id, "vifs.vif_id": vif_id},
                        dataDict, "unset")
        return True
    except Exception, e:
        log.debug("Attaching VIF %s to VM %s failed! Message: %s" %
                  (vif_id, vm_id, e))
        return None
