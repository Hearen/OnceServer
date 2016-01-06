from eve import Eve
from MyUUID import UUIDEncoder
from MyUUID import UUIDValidator

app = Eve(json_encoder=UUIDEncoder, validator=UUIDValidator)
app.run(host='133.133.135.13', port=5000, debug=True, threaded=True)
