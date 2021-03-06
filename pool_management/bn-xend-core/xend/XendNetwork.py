#============================================================================
# This library is free software; you can redistribute it and/or
# modify it under the terms of version 2.1 of the GNU Lesser General Public
# License as published by the Free Software Foundation.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
#============================================================================
# Copyright (c) 2006 Xensource Inc.
#============================================================================

import os
import commands
import re
import struct
import socket

import XendDomain
import XendNode
from XendLogging import log
from xen.xend import uuid as genuuid
from xen.util.xmlrpcclient import ServerProxy
from xen.xend.XendBase import XendBase
from xen.xend.XendError import *
from xen.util import Ovsctl
from xen.xend import XendAPIStore

IP_ROUTE_RE = r'^default via ([\d\.]+) dev (\w+)'

def _get_BNPoolAPI():
    from xen.xend.BNPoolAPI import BNPoolAPI
    return BNPoolAPI

def bridge_exists(name):
    return name in Ovsctl.get_state().keys()

def xen_rpc_call(ip, method, *args):
    """wrap rpc call to a remote host"""
    try:
        if not ip:
            raise Exception, "Invalid ip for rpc call"
        # create
        proxy = ServerProxy("http://" + ip + ":9363/")
        
        # login 
        response = proxy.session.login('root')
        if cmp(response['Status'], 'Failure') == 0:
            log.exception(response['ErrorDescription'])
            raise Exception, response['ErrorDescription']  
        session_ref = response['Value']
        
        # excute
        method_parts = method.split('_')
        method_class = method_parts[0]
        method_name  = '_'.join(method_parts[1:])
        
        if method.find("host_metrics") == 0:
            method_class = "host_metrics"
            method_name = '_'.join(method_parts[2:])
        #log.debug(method_class)
        #log.debug(method_name)
        if method_class.find("Async") == 0:
            method_class = method_class.split(".")[1]
            response = proxy.__getattr__("Async").__getattr__(method_class).__getattr__(method_name)(session_ref, *args)
        else:
            response = proxy.__getattr__(method_class).__getattr__(method_name)(session_ref, *args)
        if cmp(response['Status'], 'Failure') == 0:
            log.exception(response['ErrorDescription'])
            raise Exception, response['ErrorDescription']  
        # result
        return response.get('Value')
    except socket.error:
        raise Exception, 'socket error'

class XendNetwork(XendBase):
    """We're going to assert that the name_label of this
    network is just the name of the bridge"""

    def getClass(self):
        return "network"

    def getAttrRW(self):
        attrRW = ['name_label',
                  'name_description',
                  'other_config',
                  'default_gateway',
                  'default_netmask']
        return XendBase.getAttrRW() + attrRW

    def getAttrRO(self):
        attrRO =  ['VIFs',
                   'PIFs',
                   'managed']
        return XendBase.getAttrRO() + attrRO

    def getAttrInst(self):
        return XendBase.getAttrInst() + self.getAttrRW()

    def getMethods(self):
        methods = ['add_to_other_config',
                   'remove_from_other_config',
                   'destroy']
        return XendBase.getMethods() + methods

    def getFuncs(self):
        funcs = ['create', 'get_by_name_label', 'get_all_records_by_host']
        return XendBase.getFuncs() + funcs

    getClass    = classmethod(getClass)
    getAttrRO   = classmethod(getAttrRO)
    getAttrRW   = classmethod(getAttrRW)
    getAttrInst = classmethod(getAttrInst)
    getMethods  = classmethod(getMethods)
    getFuncs    = classmethod(getFuncs)

    def create_phy(self, name):
        """
        Called when a new bridge is found on xend start
        """
        # Create new uuids
        uuid = genuuid.gen_regularUuid()

        # Create instance
        record = {
                'name_label':       name,
                'name_description': '',
                'other_config':     {},
                'default_gateway':  '',
                'default_netmask':  '',
                'managed':          False,
            }
        network = XendNetwork(record, uuid)

        return uuid
        
    def recreate(self, record, uuid):
        """
        Called on xend start / restart, or machine
        restart, when read from saved config.
        Needs to check network exists, create it otherwise
        """

        # Create instance (do this first, to check record)
        network = XendNetwork(record, uuid)

        # Create network if it doesn't already exist
        if not bridge_exists(network.name_label):
            if network.managed:
                Ovsctl.bridge_create(network.name_label)
            else:
                log.info("Not recreating missing unmanaged network %s" % network.name_label)

        return uuid

    def create(self, record):
        """
        Called from API, to create a new network
        """
        # Create new uuids
        uuid = genuuid.gen_regularUuid()

        # Create instance (do this first, to check record)
        network = XendNetwork(record, uuid)

        # Check network doesn't already exist
        name_label = network.name_label
        if bridge_exists(name_label):
            del network
            raise UniqueNameError(name_label, "network")

        # Create the bridge
        Ovsctl.bridge_create(network.name_label)

        XendNode.instance().save_networks()

        return uuid
    
    def get_by_name_label_pool_func(self, host_ref, name):
        poolapi = _get_BNPoolAPI() 
        if poolapi._isMaster:
            if cmp(host_ref, XendNode.instance().uuid) == 0:
                return self.get_by_name_label(name)
            else:
                remote_ip = poolapi.get_host_ip(host_ref)
                return xen_rpc_call(remote_ip, 'network_get_by_name_label_pool_func', host_ref, name)
        else:
            return self.get_by_name_label(name)    

    def get_by_name_label(cls, name):
        return [inst.get_uuid()
                 for inst in XendAPIStore.get_all(cls.getClass())
                 if inst.get_name_label() == name]

    def get_all_records_by_host(self, host_ref):
        poolapi = _get_BNPoolAPI() 
        if poolapi._isMaster:
            if cmp(host_ref, XendNode.instance().uuid) == 0:
                return self.get_all_records()
            else:
                remote_ip = poolapi.get_host_ip(host_ref)
                return xen_rpc_call(remote_ip, 'network_get_all_records_by_host', host_ref)
        else:
            return self.get_all_records() 
        
    create_phy        = classmethod(create_phy)
    recreate          = classmethod(recreate)
    create            = classmethod(create)
    get_by_name_label = classmethod(get_by_name_label)
    get_all_records_by_host   = classmethod(get_all_records_by_host)
        
    def __init__(self, record, uuid):
        # This is a read-only attr, so we need to
        # set it here, as super class won't try to
        if record.has_key("managed"):
            self.managed = record["managed"]
        else:
            self.managed = True
        XendBase.__init__(self, uuid, record)
        
    #
    # XenAPI Mehtods
    #

    def destroy(self):
        # check no VIFs or PIFs attached
        if len(self.get_VIFs()) > 0:
            raise NetworkError("Cannot destroy network with VIFs attached",
                               self.get_name_label())

        if len(self.get_PIFs()) > 0:
            raise NetworkError("Cannot destroy network with PIFs attached",
                               self.get_name_label())        
        
        XendBase.destroy(self)
        Ovsctl.bridge_del(self.get_name_label())
        XendNode.instance().save_networks()

    def get_name_label(self):
        return self.name_label

    def get_name_description(self):
        return self.name_description

    def set_name_label(self, new_name):
        pass
        
    def set_name_description(self, new_desc):
        self.name_description = new_desc
        XendNode.instance().save_networks()

    def get_managed(self):
        return self.managed

    def get_VIFs(self):
        result = []
        vms = XendDomain.instance().get_all_vms()
        for vm in vms:
            vifs = vm.get_vifs()
            for vif in vifs:
                vif_cfg = vm.get_dev_xenapi_config('vif', vif)
                if vif_cfg.get('network') == self.get_uuid():
                    result.append(vif)
        return result

    def get_PIFs(self):
        pifs = XendAPIStore.get_all("PIF")
        return [pif.get_uuid() for pif in pifs
                if pif.get_network() == self.get_uuid()]

    def get_other_config(self):
        return self.other_config

    def set_other_config(self, value):
        self.other_config = value
        XendNode.instance().save_networks()

    def add_to_other_config(self, key, value):
        self.other_config[key] = value
        XendNode.instance().save_networks()

    def remove_from_other_config(self, key):
        if key in self.other_config:
            del self.other_config[key]
        XendNode.instance().save_networks()

    def get_default_gateway(self):
        return self.default_gateway

    def set_default_gateway(self, gateway):
        self.default_gateway = gateway
        XendNode.instance().save_networks()

    def get_default_netmask(self):
        return self.default_netmask

    def set_default_netmask(self, netmask):
        self.default_netmask = netmask
        XendNode.instance().save_networks()
