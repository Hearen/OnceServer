'''
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-21 09 : 18
Description : This module is specifically used to handle the database events,
            which might occur when data is fetching, fetched, inserting or
            inserted and the like;
'''
from Connection import Connection

def VMInserting(items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-21 09 : 23
    Description : when inserting resource VM, this method will be invoked;
    Usage       : app.on_insert_VM += VMInserting
    '''
    print "I am inserting VM now."


def inserting(resourceName, items):
    '''
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 09 : 54
    Description : Used to test mongoDB insertion event - any resource;
    Usage       : app.on_insert += inserting
    '''
    print "####################################"
    print "hello world"
    print resourceName
    print items
    print "uuid transferred from client"
    print items[0]["_id"]
    name = items[0]["name"]
    print name
    conn = Connection.get_libvirt_connection()
    dom = conn.lookupByName(name)
    uuidString = dom.UUIDString()
    print uuidString
    print 'trying to replace uuit'
    items[0]["_id"] = uuidString
    print "####################################"

