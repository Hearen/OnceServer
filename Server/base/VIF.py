import traceback
from utils.Connection import Connection
from utils.OnceLogging import log, init
from utils.XmlConverter import XmlConverter
from utils.DBHelper import VIFHelper

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
        log.debug("VIF.delete Failed! Message: %s" %e)
        return None
    return True
