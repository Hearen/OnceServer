import xml.etree.ElementTree as ET
class XMLConverter():
    @staticmethod
    def toVMXml(name, memory, vcpu, image, tap2, vif, vbd, vfb, console):
        root = ET.Element("domain")
        root.set("type", 'xen')

        nameET = ET.SubElement(root, "name")
        nameET.text = name

        memElement = ET.SubElement(root, "memory")
        memory = int(memory) * 1024
        print memory
        memElement.text = str(memory)
        memElement.set("unit", 'KiB')

        currentmemElement = ET.SubElement(root, "currentmemory")
        memory = int(memory) * 1024
        print type(memory)
        print memory
        currentmemElement.text = str(memory)
        currentmemElement.set("unit", 'KiB')

        vcpu = ET.SubElement(root, "vcpu")
        vcpu.text = vcpu
        vcpu.set("placement", 'static')

        os = ET.SubElement(root,"os")

        features = ET.SubElement(root,"features")
        acpi = ET.SubElement(features,"acpi")
        apic = ET.SubElement(features,"apic")
        pae = ET.SubElement(features,"pae")

        clock = ET.SubElement(root,"clock")
        clock.set("offset",'utc')

        on_power = ET.SubElement(root,"on_power")
        on_power.text = "destroy"
        on_reboot = ET.SubElement(root,"on_reboot")
        on_reboot.text = "destroy"
        on_crash = ET.SubElement(root,"on_crash")
        on_crash.text = "destroy"

        device = ['cdrom','hd']
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
        backingStore = ET.SubElement(disk,"backingStore")
        target = ET.SubElement(disk,"target")
        target.set("dev",vbd['dev'][0:3])
        target.set("bus",'virtio')
        if vbd['mode'] == 'r':
            readonly = ET.SubElement(disk, "readonly")
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
        backingStore = ET.SubElement(disk,"backingStore")
        target = ET.SubElement(disk,"target")
        target.set("dev",tap2['dev'][0:3])
        target.set("bus",'virtio')
        if tap2['mode'] == 'r':
            readonly = ET.SubElement(disk, "readonly")
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
        # mac = ET.SubElement(interface,"mac")
        # mac.set("address",vif['mac'])
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
        model.set("vram","8192")
        model.set("heads",'1')
        rough_string = ET.tostring(root, 'utf-8')

        print rough_string
        return rough_string
