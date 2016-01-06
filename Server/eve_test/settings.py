invoices = {
    # this resource item endpoint (/invoices/<id>) will match a UUID regex.
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
    'item_url': 'regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")',
    # 'item_url': 'regex(".+")',
    'schema': {
        # set our _id field of our custom uuid type.
        '_id': {
            'type': 'uuid',
            'unique': True,
        },
        'name': {'type': 'string'}
    },

}

DOMAIN = {
    'invoices': invoices
}
