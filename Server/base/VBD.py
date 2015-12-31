from utils.Connection import Connection
from utils.OnceLogging import log, init
from utils.XmlConverter import XmlConverter
from utils.libvirt import libvirtError
from utils.DBHelper import VBDHelper

init("/var/log/xen/libvirt.log", "DEBUG", log)
conn = Connection.get_libvirt_connection()

def createPool(_id, name, target):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 10 : 17
    Description : Using indispensible factors to create a pool;
    '''
    config = XmlConverter.toSRXml(_id, name, target)
    print "pool xml config"
    print config
    print "pool xml config end"
    try:
        conn.poolCreateXML(config)
    except libvirtError, e:
        log.debug("pool %s creation failed! Message: %s" % (name, e))
        return False
    return True

def deletePool(_id):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 10 : 17
    Description : Delete the pool specified by a name;
    '''
    try:
        pool = conn.storagePoolLookupByUUIDString(_id)
    except libvirtError, e:
        log.debug("pool %s not found! Message: %s" % (_id, e))
        return "Pool not found"
    if pool.isActive():
        pool.destroy()
    try:
        pool.undefine()
        filter = {"_id": _id}
        VBDHelper.removePool(filter)
    except libvirtError, e:
        log.debug("pool %s cannot be removed! Message: %s" % (_id, e))
        return "Pool is busy!"
    return True

def listPools():
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 10 : 25
    Description : Used to list all known pools;
    '''
    try:
        poolNames = conn.listAllStoragePoolsNames()
    except libvirtError, e:
        log.debug("pool listing error! Message: %s" % e)
        return False
    if len(poolNames) > 0:
        return ','.join(poolNames)
    else:
        return ''

def createVolume(_id, poolName, volName, volSize):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 15 : 43
    Description : Create a volume in a existed pool specified by a pool name;
    '''
    try:
        pool = conn.storagePoolLookupByName(poolName)
    except libvirtError, e:
        log.debug("pool %s not found! Message: %s" % (poolName, e))
        return False
    config = XmlConverter.toVolumeXml(volName, volSize)
    if not pool.isActive():
        pool.create()
    try:
        pool.createXML(config)
    except libvirtError, e:
        log.debug("Volume %s creation failed! Message: %s" % (volName, e))
        return False
    return True

def deleteVolume(_id, poolName, volName):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 10 : 31
    Description : Used to delete a volume in a specified pool;
    '''
    '''
    Added by    : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 16:19
    Description : _id is currently designed to handle DB separately;
    '''
    try:
        pool = conn.storagePoolLookupByName(poolName)
    except libvirtError, e:
        log.debug("pool %s not found! Message: %s" % (poolName, e))
        return False
    try:
        volume = pool.storageVolLookupByName(volName)
    except libvirtError, e:
        log.debug("volume %ss not found! Message: %s" % (volName, e))
        return False
    try:
        volume.delete()
        filter = {"_id": _id}
        VBDHelper.removeVolume(filter)
    except libvirtError, e:
        log.debug("Volume %s deletion failed! Message: %s" % (volName, e))
        return False
    return True

def listVolumes(poolName):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-30 16:24
    Description : Used to list volume names in a pool;
    '''
    try:
        pool = conn.storagePoolLookupByName(poolName)
    except libvirtError, e:
        log.debug("pool %s not found! Message: %s" % (poolName, e))
        return False
    try:
        volumes = pool.listAllVolumes()
    except libvirtError, e:
        log.debug("%s pool.listAllVolumes method failed! Message: %s" % (poolName, e))
        return False
    volNames = ','.join([vol.name() for vol in volumes])
    return volNames
