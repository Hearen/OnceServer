# -*- coding: utf-8 -*-

import os

# We want to seamlessy run our API both locally and on Heroku. If running on
# Heroku, sensible DB connection settings are stored in environment variables.
# MONGO_HOST = os.environ.get('MONGO_HOST', 'localhost')
# MONGO_PORT = os.environ.get('MONGO_PORT', 27017)
# MONGO_USERNAME = os.environ.get('MONGO_USERNAME', 'user')
# MONGO_PASSWORD = os.environ.get('MONGO_PASSWORD', 'user')
# MONGO_DBNAME = os.environ.get('MONGO_DBNAME', 'evedemo')
#

# Enable reads (GET), inserts (POST) and DELETE for resources/collections
# (if you omit this line, the API will default to ['GET'] and provide
# read-only access to the endpoint).
RESOURCE_METHODS = ['GET', 'POST', 'DELETE']

# Enable reads (GET), edits (PATCH) and deletes of individual items
# (defaults to read-only item access).
ITEM_METHODS = ['GET', 'PATCH', 'DELETE']

IF_MATCH = False

# We enable standard client cache directives for all resources exposed by the
# API. We can always override these global settings later.
CACHE_CONTROL = 'max-age=20'
CACHE_EXPIRES = 20

vm = {
    'additional_lookup': {
        'url': 'regex("[\w]+")',
        'field': 'uuid'
    },
    'id_field': 'uuid',
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
    'schema': {
         'uuid': {
			'type': 'string',
			'required': True,
            'unique': True,
         },
		 'name': {
			'type': 'string',
            'required': True,
		 },
		 'vcpu': {
			'type': 'integer',
            'required': True,
		 },
		 'memory': {
			'type': 'integer',
            'required': True,
		 },
        'diskDir':{
            'type': 'string',
            'required': True,
        },
        'isoDir':{
            'type':'string',
            'required': True,
        },
        'bridgeSrc':{
            'type':'string',
            'required': True,
        },
		 'VBDs': {
			'type': 'list',
		 },
		 'VIFs': {
			'type': 'list',
		 },
		 'is_template': {
			'type': 'boolean',
             'default': False,
		 },
        'powerstate': {
            'type': 'string',
        },
    }
}

vif = {
    'additional_lookup': {
        'url': 'regex("[\w]+")',
        'field': 'uuid'
    },
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
	'schema': {
		'uuid': {
			'type': 'string',
			'required': True,
            'unique': True,
		},
		'name': {
			'type': 'string',
		},
		'MAC': {
			'type': 'string',
		},
		'bridge': {
			'type': 'string',
		},
	}
}

vbd = {
    'additional_lookup': {
        'url': 'regex("[\w]+")',
        'field': 'uuid'
    },
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
	'schema': {
		'uuid': {
			'type': 'string',
			'required': True,
            'unique': True,
		},
		'name': {
			'type': 'string',
		},
		'path': {
			'type': 'string',
		},
		'mode': {
			'type': 'string',
		},
		'bootable': {
			'type': 'boolean',
		},
		'type': {
			'type': 'string',
		},
		'device': {
			'type': 'string',
		},
	}
}

# The DOMAIN dict explains which resources will be available and how they will
# be accessible to the API consumer.
DOMAIN = {
    'VM': vm,
	'VIF': vif,
	'VBD': vbd,
}
