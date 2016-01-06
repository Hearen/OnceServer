# -*- coding: utf-8 -*-
import os
# We want to seamlessy run our API both locally and on Heroku. If running on
# Heroku, sensible DB connection settings are stored in environment variables.
MONGO_HOST = os.environ.get('MONGO_HOST', 'localhost')
MONGO_PORT = os.environ.get('MONGO_PORT', 27017)
MONGO_DBNAME = os.environ.get('MONGO_DBNAME', 'server')
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
    'item_url': 'regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")',
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
    'schema': {
        '_id':{
            'type': 'uuid',
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
        'mac':{
            'type': 'string',
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
		 'isTemplate': {
			'type': 'string',
             'default': 'False',
		 },
    }
}

vif = {
    'item_url': 'regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")',
#     'additional_lookup': {
#         'url': 'regex("[\w]+")',
#         'field': 'uuid'
#     },
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
	'schema': {
        '_id':{
            'type': 'uuid',
            'unique': True,
        },
		'name': {
			'type': 'string',
		},
		'macString': {
			'type': 'string',
		},
		'source': {
			'type': 'string',
		},
	}
}

pool = {
    'item_url': 'regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")',
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
    'schema': {
        '_id':{
            'type': 'uuid',
            'unique': True,
        },
		 'name': {
			'type': 'string',
            'required': True,
		 },
		 'target': {
			'type': 'string',
            'required': True,
		 },
        'volumes': {
            'type': 'list',
        }
    }
}

volume = {
    'item_url': 'regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")',
    'cache_control': 'max-age=10,must-revalidate',
    'cache_expires': 10,
    # most global settings can be overridden at resource level
    'resource_methods': ['GET', 'POST', 'DELETE'],
    'item_methods': ['GET', 'PATCH', 'DELETE', 'PUT'],
    'schema': {
        '_id':{
            'type': 'uuid',
            'unique': True,
        },
		 'poolName': {
			'type': 'string',
            'required': True,
		 },
		 'volName': {
			'type': 'string',
            'required': True,
		 },
		 'volSize': {
			'type': 'integer',
            'required': True,
		 },
    }
}

# The DOMAIN dict explains which resources will be available and how they will
# be accessible to the API consumer.
DOMAIN = {
    'VMs': vm,
	'VIFs': vif,
	'StoragePools': pool,
    'Volumes':volume,
}
