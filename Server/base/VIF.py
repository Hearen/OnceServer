import traceback
from utils.Connection import Connection
from utils.OnceLogging import log, init
from utils.XmlConverter import XmlConverter
from time import sleep
from utils.DBHelper import VMHelper

init("/var/log/xen/libvirt.log", "DEBUG", log)
conn = Connection.get_libvirt_connection()

def create(_id, vm_id, net_type, mac, source, flags=0):
    '''
    added by Wu Yuewen
    E-mail: wuyuewen@otcaix.iscas.ac.cn
    Used to define a VIF and attach it to a VM
    VIR_DOMAIN_AFFECT_CURRENT - 0 
    specifies that the device allocation is made based on current domain state.  
    VIR_DOMAIN_AFFECT_LIVE - 1 
    specifies that the device shall be allocated to the active domain instance 
    only and is not added to the persisted domain configuration.  
    VIR_DOMAIN_AFFECT_CONFIG - 2
    specifies that the device shall be allocated to the persisted domain
    configuration only.
    '''
    try:
        dom = conn.lookupByUUIDString(vm_id)
        if dom:
            xml_config = XmlConverter.toVIFXml(net_type, mac, source)
            dom.attachDeviceFlags(xml_config, flags)
        return True
    except Exception:
        log.exception(traceback.print_exc())
        return False
    
def delete(_id, vm_id, net_type, mac, source, flags=0):
    '''
    added by Wu Yuewen
    E-mail: wuyuewen@otcaix.iscas.ac.cn
    Used to define a VIF and attach it to a VM
    VIR_DOMAIN_AFFECT_CURRENT - 0 
    specifies that the device allocation is made based on current domain state.  
    VIR_DOMAIN_AFFECT_LIVE - 1 
    specifies that the device shall be allocated to the active domain instance 
    only and is not added to the persisted domain configuration.  
    VIR_DOMAIN_AFFECT_CONFIG - 2
    specifies that the device shall be allocated to the persisted domain
    configuration only.
    '''
    try:
        dom = conn.lookupByUUIDString(vm_id)
        if dom:
            xml_config = XmlConverter.toVIFXml(None, mac, None)
            dom.detachDeviceFlags(xml_config, flags)
        return True
    except Exception:
        log.exception(traceback.print_exc())
        return False