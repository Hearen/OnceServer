import xml.etree.ElementTree as ET
from utils.OnceLogging import log, init
init("/var/log/xen/libvirt.log", "DEBUG", log)

class XmlConverter():
    '''
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-14 15:51
    Description: Use limited parameters to return XML configuration string;
    '''
    @staticmethod
    def toVMXml(uuid, name, memory, vcpu, image, tap2, vif, vbd, vfb, console):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-23 15 : 31
        Description : Using essential values to generate
                a XML configuration string;
        '''
        root = ET.Element("domain")
        root.set("type", 'xen')

        if uuid is not None:
            uuidET = ET.SubElement(root, "uuid")
            uuidET.text = uuid

        nameET = ET.SubElement(root, "name")
        nameET.text = name

        memElement = ET.SubElement(root, "memory")
        memory = int(memory) * 1024
        memElement.text = str(memory)
        memElement.set("unit", 'KiB')

        currentmemElement = ET.SubElement(root, "currentmemory")
        memory = int(memory) * 1024
        currentmemElement.text = str(memory)
        currentmemElement.set("unit", 'KiB')

        vcpuET = ET.SubElement(root, "vcpu")
        vcpuET.text = vcpu
        vcpuET.set("placement", 'static')

        os = ET.SubElement(root,"os")

        features = ET.SubElement(root,"features")
        ET.SubElement(features,"acpi")
        ET.SubElement(features,"apic")
        ET.SubElement(features,"pae")

        clock = ET.SubElement(root,"clock")
        clock.set("offset",'utc')

        on_power = ET.SubElement(root,"on_power")
        on_power.text = "destroy"
        on_reboot = ET.SubElement(root,"on_reboot")
        on_reboot.text = "destroy"
        on_crash = ET.SubElement(root,"on_crash")
        on_crash.text = "destroy"

        device = ['hd', 'cdrom']
        for key in image:
            typeET = ET.SubElement(os, "type")
            typeET.text = key
            typeET.set("arch", 'x86_64')
            typeET.set("machine", 'xenfy')

            loader = ET.SubElement(os, "loader")
            loader.text = image[key]['loader']
            loader.set("type", 'rom')

            boot = ET.SubElement(os,"boot")
            boot.set("dev",image[key]['boot'])

            device.remove(image[key]['boot'])
            for element in device:
                boot = ET.SubElement(os,"boot")
                boot.set("dev",str(element))

            devices = ET.SubElement(root,"devices")
            emulator = ET.SubElement(devices,"emulator")
            emulator.text = image[key]['device_model']

        disk = ET.SubElement(devices, "disk")
        disk.set("type","file")
        disk.set("device",vbd['dev'][4:])
        source = ET.SubElement(disk,"source")
        source.set("file",vbd['uname'][8:])
        ET.SubElement(disk,"backingStore")
        target = ET.SubElement(disk,"target")
        target.set("dev",vbd['dev'][0:3])
        target.set("bus",'virtio')
        if vbd['mode'] == 'r':
            ET.SubElement(disk, "readonly")
        address = ET.SubElement(disk,"address")
        address.set("type","drive")
        address.set("controller",'0')
        address.set("bus",'1')
        address.set("target",'0')
        address.set("unit",'0')

        disk = ET.SubElement(devices, "disk")
        disk.set("type","file")
        disk.set("device",tap2['dev'][4:])
        source = ET.SubElement(disk,"source")
        source.set("file",tap2['uname'][8:])
        ET.SubElement(disk,"backingStore")
        target = ET.SubElement(disk,"target")
        target.set("dev",tap2['dev'][0:3])
        target.set("bus",'virtio')
        if tap2['mode'] == 'r':
            ET.SubElement(disk, "readonly")
        address = ET.SubElement(disk,"address")
        address.set("type","drive")
        address.set("controller",'0')
        address.set("bus",'1')
        address.set("target",'0')
        address.set("unit",'0')

        controller = ET.SubElement(devices,"controller")
        controller.set("type",'usb')
        controller.set("index",'0')
        controller.set("model",'ich9-ehci1')
        for i in range(0,1):
            controller = ET.SubElement(devices,"controller")
            controller.set("type",'usb')
            controller.set("index",'0')
            controller.set("model",'ich9-ehci'+str(i+1))
            master = ET.SubElement(controller,"master")
            master.set("startport",str(i*2))

        interface = ET.SubElement(devices,"interface")
        interface.set("type",'bridge')
        if "mac" in interface:
            mac = ET.SubElement(interface,"mac")
            mac.set("address",vif['mac'])
        source = ET.SubElement(interface,"source")
        source.set("bridge",vif['bridge'])

        serial = ET.SubElement(devices,"serial")
        serial.set("type",'pty')
        target = ET.SubElement(serial,"target")
        target.set("port",'0')

        consoleET = ET.SubElement(devices,"console")
        consoleET.set("type",'pty')
        target = ET.SubElement(consoleET,"target")
        target.set("type",'serial')
        target.set("port",console['location'])

        input = ET.SubElement(devices,"input")
        input.set("type","tablet")
        input.set("bus","usb")

        graphics = ET.SubElement(devices, "graphics")
        graphics.set("type", 'vnc')
        graphics.set("port", vfb['location'][8:])
        graphics.set("autoport", 'yes')
        graphics.set("listen", vfb['location'][0:7])
        listen = ET.SubElement(graphics,"listen")
        listen.set("type","address")
        listen.set("address",vfb['vnclisten'])

        video = ET.SubElement(devices,"video")
        model = ET.SubElement(video,"model")
        model.set("type","vga")
        model.set("vram","81920")
        model.set("heads",'1')
        rough_string = ET.tostring(root, 'utf-8')
        return rough_string


    @staticmethod
    def toSRXml(id, name, target, poolType='dir'):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-23 15 : 42
        Description : Used to describe the storage pool configuration
                    to create it;
        '''
        print name
        print target
        print poolType
        root = ET.Element("pool")
        root.set("type", poolType)
        idET = ET.SubElement(root, "uuid")
        idET.text = id
        nameET = ET.SubElement(root, "name")
        nameET.text = name
        targetET = ET.SubElement(root, "target")
        pathET = ET.SubElement(targetET, "path")
        pathET.text = target

        return ET.tostring(root, 'utf-8')

    @staticmethod
    def toVolumeXml(volumeName, volumeSize):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2015-12-23 16 : 30
        Description : Used to define a XML configuration string to create a
                    volume within a active pool;
        '''
        root = ET.Element("volume")
        nameET = ET.SubElement(root,"name")
        nameET.text = volumeName
        capacityET = ET.SubElement(root, "capacity")
        capacityET.set("unit", "M")
        capacityET.text = volumeSize
        return ET.tostring(root, 'utf-8')

    @staticmethod
    def toNetXml(uuidString, name, source, macString):
        '''
        Author      : LHearen
        E-mail      : LHearen@126.com
        Time        : 2016-01-05 10:10
        Description : Used to create or define a virtual network card;
        '''
        root = ET.Element("network")
        nameET = ET.SubElement(root, "name")
        nameET.text = name
        uuidET = ET.SubElement(root, "uuid")
        uuidET.text = uuidString
        bridgeET = ET.SubElement(root, "bridge")
        bridgeET.set("name", source)
        if macString == None:
            macET = ET.SubElement(root, "mac")
            macET.set("address", macString)
        netXml = ET.tostring(root, 'utf-8')
        return netXml

    @staticmethod
    def toVIFXml(net_type, mac, source):
        '''
        Author      : Wu Yuewen
        E-mail      : wuyuewen@otcaix.iscas.ac.cn
        Time        : 2015-12-24 14 : 50
        Description : Used to define a XML configuration string to create a
                    interface;
        '''
        root = ET.Element('interface')
        if net_type:
            root.set('type', net_type)
        if mac:
            mac_addr = ET.SubElement(root, "mac")
            mac_addr.set("address", str(mac))
        if source:
            source_bridge = ET.SubElement(root, 'source')
            source_bridge.set('bridge', source)
        vif_xml = ET.tostring(root, 'utf-8')
        log.debug(vif_xml)
        return vif_xml

