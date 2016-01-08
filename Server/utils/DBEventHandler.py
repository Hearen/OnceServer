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
    import sys
    sys.path.append(RootDir)
    from base.VM import UUIDString
    from base.VM import Name
    items[0]["name"] = Name
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
    from base.VBD import VolName
    items[0]['volName'] = VolName
    items[0]['_id'] = VolumeUUIDString

def PoolInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2016-01-04 15:32
    Description : Use the right UUIDString determined by base.VBD instead of the default;
    '''
    import sys
    sys.path.append(RootDir)
    from base.VBD import PoolUUIDString
    from base.VBD import PoolName
    items[0]['name'] = PoolName
    items[0]['_id'] = PoolUUIDString

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
    from base.VIF import VIFUUIDString
    print items[0]['_id']
    items[0]['_id'] = VIFUUIDString
    print "replacing VIF uuidstring"
