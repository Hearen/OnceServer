from eve import Eve
from api.utils.MyUUID import UUIDEncoder
from api.utils.MyUUID import UUIDValidator
app = Eve(__name__, json_encoder=UUIDEncoder, validator=UUIDValidator)
import api.utils.RequestHandler

