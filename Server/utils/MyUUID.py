from eve.io.mongo import Validator
from eve.io.base import BaseJSONEncoder
from uuid import UUID
'''
Author: LHearen
E-mail: LHearen@126.com
Time  :	2015-12-14 15:44
Description: Enable the uuid to replace default id to suit libvirt management;
'''
class UUIDEncoder(BaseJSONEncoder):
    '''
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-14 14:45
    Description: Used to encode UUID;
    '''
    def default(self, obj):
        if isinstance(obj, UUID):
            return str(obj)
        else:
            '''
            Other types will be serialized by base class;
            '''
            return super(UUIDEncoder, self).default(obj)

class UUIDValidator(Validator):
    '''
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-14 14:47
    Description: Extends the base mongo validator adding support for
                the uuid data-type;
    '''
    def _validate_type_uuid(self, field, value):
        try:
            UUID(value)
        except ValueError:
            self._error(field, "value '%s' cannot be converted to a UUID" %
                        value)



