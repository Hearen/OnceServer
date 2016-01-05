'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-21 09 : 18
Description : This module is specifically used to handle the database events,
            which might occur when data is fetching, fetched, inserting or
            inserted and the like;
'''
from CONST import RootDir

def VMInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-21 09 : 23
    Description : when inserting resource VM, this method will be invoked;
    Usage       : app.on_insert_VM += VMInserting
    '''
    print "I am inserting VM now."
    print items[0]["_id"]
    import sys
    sys.path.append(RootDir)
    from base.VM import UUIDString
    print UUIDString
    print 'trying to replace uuid'
    items[0]["_id"] = UUIDString



def inserting(resourceName, items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 09 : 54
    Description : Used to test mongoDB insertion event - any resource;
    Usage       : app.on_insert += inserting
    '''
    print "\n\ninserting db\n\n"
    print resourceName
    print items
    print items[0]

def VolumeInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-04 15:32
    Description : Use the right UUIDString determined by base.VBD instead of the default;
    '''
    print "inside VolumeInserting"
    import sys
    sys.path.append(RootDir)
    from base.VBD import VolumeUUIDString
    print items[0]['_id']
    items[0]['_id'] = VolumeUUIDString
    print "replacing volume uuidstring"

def PoolInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-04 15:32
    Description : Use the right UUIDString determined by base.VBD instead of the default;
    '''
    print "inside PoolInserting"
    import sys
    sys.path.append(RootDir)
    from base.VBD import PoolUUIDString
    print items[0]['_id']
    items[0]['_id'] = PoolUUIDString
    print "replacing pool uuidstring"

def VIFInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-04 15:32
    Description : Use the right UUIDString determined by base.VIF instead of the default;
    '''
    print "inside VIFInserting"
    import sys
    sys.path.append(RootDir)
    from base.VBD import VIFUUIDString
    print items[0]['_id']
    items[0]['_id'] = VIFUUIDString
    print "replacing VIF uuidstring"
