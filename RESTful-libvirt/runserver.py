from server import app
from server.api.Connection import Connection

def replace_uuid(items):
    print "I am inserting VM now."

def hello(resourceName, items):
    print "####################################"
    print "hello world"
    print resourceName
    print items
    print "uuid transferred from client"
    print items[0]["uuid"]
    name = items[0]["name"]
    print name
    conn = Connection.get_libvirt_connection()
    dom = conn.lookupByName(name)
    uuidString = dom.UUIDString()
    print uuidString
    print 'trying to replace uuit'
    items[0]["uuid"] = uuidString
    print "####################################"

app.on_insert += hello
app.on_insert_VM += replace_uuid
app.run(host='0.0.0.0', port=5100, debug=True, threaded=True)
