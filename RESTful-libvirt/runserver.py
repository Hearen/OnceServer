from server import app
def replace_uuid(resource, updates, original):
    print "I am inserting VM now."
app.run(host='0.0.0.0', port=5100, debug=True, threaded=True)
app.on_insert_VM += replace_uuid
