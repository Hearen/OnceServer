/*
 * Copyright (c) Citrix Systems, Inc.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of version 2 of the GNU General Public License as published
 * by the Free Software Foundation, with the additional linking exception as
 * follows:
 * 
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 * 
 *   As a special exception, the copyright holders of this library give you
 *   permission to link this library with independent modules to produce an
 *   executable, regardless of the license terms of these independent modules,
 *   and to copy and distribute the resulting executable under terms of your
 *   choice, provided that you also meet, for each linked independent module,
 *   the terms and conditions of the license of that module. An independent
 *   module is a module which is not derived from or based on this library. If
 *   you modify this library, you may extend this exception to your version of
 *   the library, but you are not obligated to do so. If you do not wish to do
 *   so, delete this exception statement from your version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.beyondsphere.xenapi;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.VersionException;
import com.beyondsphere.xenapi.Types.XenAPIException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A storage repository
 * 
 * @author Citrix Systems, Inc.
 */
public class SR extends XenAPIObject {

	/**
	 * The XenAPI reference to this object.
	 */
	protected final String ref;

	/**
	 * For internal use only.
	 */
	SR(String ref) {
		this.ref = ref;
	}

	public String toWireString() {
		return this.ref;
	}

	/**
	 * If obj is a SR, compares XenAPI references for equality.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof SR) {
			SR other = (SR) obj;
			return other.ref.equals(this.ref);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return ref.hashCode();
	}

	/**
	 * Represents all the fields in a SR
	 */
	public static class Record implements Types.Record {

		public long timestamp;

		public Record() {
			timestamp = System.currentTimeMillis();
		}

		public String toString() {
			StringWriter writer = new StringWriter();
			PrintWriter print = new PrintWriter(writer);
			print.printf("%1$20s: %2$s\n", "uuid", this.uuid);
			print.printf("%1$20s: %2$s\n", "nameLabel", this.nameLabel);
			print.printf("%1$20s: %2$s\n", "nameDescription",
					this.nameDescription);
			print.printf("%1$20s: %2$s\n", "residentOn", this.residentOn);
//			print.printf("%1$20s: %2$s\n", "allowedOperations",
//					this.allowedOperations);
//			print.printf("%1$20s: %2$s\n", "currentOperations",
//					this.currentOperations);
			print.printf("%1$20s: %2$s\n", "VDIs", this.VDIs);
//			print.printf("%1$20s: %2$s\n", "PBDs", this.PBDs);
			print.printf("%1$20s: %2$s\n", "virtualAllocation",
					this.virtualAllocation);
			print.printf("%1$20s: %2$s\n", "physicalUtilisation",
					this.physicalUtilisation);
			print.printf("%1$20s: %2$s\n", "physicalSize", this.physicalSize);
			print.printf("%1$20s: %2$s\n", "type", this.type);
			print.printf("%1$20s: %2$s\n", "contentType", this.contentType);
			print.printf("%1$20s: %2$s\n", "shared", this.shared);
			print.printf("%1$20s: %2$s\n", "otherConfig", this.otherConfig);
			print.printf("%1$20s: %2$s\n", "tags", this.tags);
			print.printf("%1$20s: %2$s\n", "smConfig", this.smConfig);
//			print.printf("%1$20s: %2$s\n", "blobs", this.blobs);
//			print.printf("%1$20s: %2$s\n", "localCacheEnabled",
//					this.localCacheEnabled);
//			print.printf("%1$20s: %2$s\n", "introducedBy", this.introducedBy);
			print.printf("%1$20s: %2$s\n", "timestamp", this.timestamp);
			return writer.toString();
		}

		/**
		 * Convert a SR.Record to a Map
		 */
		public Map<String, Object> toMap() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uuid", this.uuid == null ? "" : this.uuid);
			map.put("name_label", this.nameLabel == null ? "" : this.nameLabel);
			map.put("name_description", this.nameDescription == null ? ""
					: this.nameDescription);
			map.put("resident_on", this.residentOn == null ? new Host(
					"OpaqueRef:NULL") : this.residentOn);
//			map.put("allowed_operations",
//					this.allowedOperations == null ? new LinkedHashSet<Types.StorageOperations>()
//							: this.allowedOperations);
//			map.put("current_operations",
//					this.currentOperations == null ? new HashMap<String, Types.StorageOperations>()
//							: this.currentOperations);
			map.put("VDIs", this.VDIs == null ? new LinkedHashSet<VDI>()
					: this.VDIs);
//			map.put("PBDs", this.PBDs == null ? new LinkedHashSet<PBD>()
//					: this.PBDs);
			map.put("virtual_allocation", this.virtualAllocation == null ? 0
					: this.virtualAllocation);
			map.put("physical_utilisation",
					this.physicalUtilisation == null ? 0
							: this.physicalUtilisation);
			map.put("physical_size", this.physicalSize == null ? 0
					: this.physicalSize);
			map.put("type", this.type == null ? "" : this.type);
			map.put("content_type", this.contentType == null ? ""
					: this.contentType);
			map.put("shared", this.shared == null ? false : this.shared);
			map.put("other_config",
					this.otherConfig == null ? new HashMap<String, String>()
							: this.otherConfig);
			map.put("tags", this.tags == null ? new LinkedHashSet<String>()
					: this.tags);
			map.put("sm_config",
					this.smConfig == null ? new HashMap<String, String>()
							: this.smConfig);
//			map.put("blobs", this.blobs == null ? new HashMap<String, Blob>()
//					: this.blobs);
//			map.put("local_cache_enabled",
//					this.localCacheEnabled == null ? false
//							: this.localCacheEnabled);
//			map.put("introduced_by", this.introducedBy == null ? new DRTask(
//					"OpaqueRef:NULL") : this.introducedBy);
			return map;
		}

		/**
		 * Unique identifier/object reference
		 */
		public String uuid;
		/**
		 * a human-readable name
		 */
		public String nameLabel;
		/**
		 * a notes field containg human-readable description
		 */
		public String nameDescription;
		/**
		 * resident on which host.
		 */
		public Host residentOn;
//		/**
//		 * list of the operations allowed in this state. This list is advisory
//		 * only and the server state may have changed by the time this field is
//		 * read by a client.
//		 */
//		public Set<Types.StorageOperations> allowedOperations;
//		/**
//		 * links each of the running tasks using this object (by reference) to a
//		 * current_operation enum which describes the nature of the task.
//		 */
//		public Map<String, Types.StorageOperations> currentOperations;
		/**
		 * all virtual disks known to this storage repository
		 */
		public Set<VDI> VDIs;
//		/**
//		 * describes how particular hosts can see this storage repository
//		 */
//		public Set<PBD> PBDs;
		/**
		 * sum of virtual_sizes of all VDIs in this storage repository (in
		 * bytes)
		 */
		public Long virtualAllocation;
		/**
		 * physical space currently utilised on this storage repository (in
		 * bytes). Note that for sparse disk formats, physical_utilisation may
		 * be less than virtual_allocation
		 */
		public Long physicalUtilisation;
		/**
		 * total physical size of the repository (in bytes)
		 */
		public Long physicalSize;
		/**
		 * type of the storage repository
		 */
		public String type;
		/**
		 * the type of the SR's content, if required (e.g. ISOs)
		 */
		public String contentType;
		/**
		 * true if this SR is (capable of being) shared between multiple hosts
		 */
		public Boolean shared;
		/**
		 * additional configuration
		 */
		public Map<String, String> otherConfig;
		/**
		 * user-specified tags for categorization purposes
		 */
		public Set<String> tags;
		/**
		 * SM dependent data
		 */
		public Map<String, String> smConfig;
//		/**
//		 * Binary blobs associated with this SR
//		 */
//		public Map<String, Blob> blobs;
//		/**
//		 * True if this SR is assigned to be the local cache for its host
//		 */
//		public Boolean localCacheEnabled;
//		/**
//		 * The disaster recovery task which introduced this SR
//		 */
//		public DRTask introducedBy;
	}

	/**
	 * Get a record containing the current state of the given SR.
	 * 
	 * @return all fields from the object
	 */
	public SR.Record getRecord(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_record";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		// System.out.println();
		return Types.toSRRecord(result);
	}

	/**
	 * Get a reference to the SR instance with the specified UUID.
	 * 
	 * @param uuid
	 *            UUID of object to return
	 * @return reference to the object
	 */
	public static SR getByUuid(Connection c, String uuid)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.get_by_uuid";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(uuid) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSR(result);
	}

	/**
	 * Get all the SR instances with the given label.
	 * 
	 * @param label
	 *            label of object to return
	 * @return references to objects with matching names
	 */
	public static Set<SR> getByNameLabel(Connection c, String label)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.get_by_name_label";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(label) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfSR(result);
	}

	/**
	 * Get all the SR instances with the given type.
	 * 
	 * @param type
	 *            type of object to return
	 * @return references to objects with matching names
	 */
	public static Set<SR> getByType(Connection c, String type)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.get_by_type";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(type) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfSR(result);
	}

	/**
	 * Get the uuid field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getUuid(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_uuid";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the name/label field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getNameLabel(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_name_label";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the name/description field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getNameDescription(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_name_description";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the allowed_operations field of the given SR.
	 * 
	 * @return value of the field
	 */
	// public Set<Types.StorageOperations> getAllowedOperations(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.get_allowed_operations";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// Object result = response.get("Value");
	// return Types.toSetOfStorageOperations(result);
	// }

//	/**
//	 * Get the current_operations field of the given SR.
//	 * 
//	 * @return value of the field
//	 */
//	public Map<String, Types.StorageOperations> getCurrentOperations(
//			Connection c) throws BadServerResponse, XenAPIException,
//			XmlRpcException {
//		String method_call = "SR.get_current_operations";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toMapOfStringStorageOperations(result);
//	}

	/**
	 * Get the VDIs field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Set<VDI> getVDIs(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_VDIs";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfVDI(result);
	}

//	/**
//	 * Get the PBDs field of the given SR.
//	 * 
//	 * @return value of the field
//	 */
//	public Set<PBD> getPBDs(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.get_PBDs";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toSetOfPBD(result);
//	}

	/**
	 * Get the virtual_allocation field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Long getVirtualAllocation(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_virtual_allocation";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toLong(result);
	}

	/**
	 * Get the physical_utilisation field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Long getPhysicalUtilisation(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_physical_utilisation";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toLong(result);
	}

	/**
	 * Get the physical_size field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Long getPhysicalSize(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_physical_size";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toLong(result);
	}

	/**
	 * Get the type field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getType(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_type";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the content_type field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getContentType(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_content_type";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Get the shared field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Boolean getShared(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_shared";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toBoolean(result);
	}

	/**
	 * Get the other_config field of the given SR.
	 * 
	 * @return value of the field
	 */
	// public Map<String, String> getOtherConfig(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.get_other_config";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// Object result = response.get("Value");
	// return Types.toMapOfStringString(result);
	// }

	/**
	 * Get the tags field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Set<String> getTags(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_tags";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfString(result);
	}

	/**
	 * Get the sm_config field of the given SR.
	 * 
	 * @return value of the field
	 */
	public Map<String, String> getSmConfig(Connection c)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.get_sm_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toMapOfStringString(result);
	}

//	/**
//	 * Get the blobs field of the given SR.
//	 * 
//	 * @return value of the field
//	 */
//	public Map<String, Blob> getBlobs(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.get_blobs";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toMapOfStringBlob(result);
//	}

//	/**
//	 * Get the local_cache_enabled field of the given SR.
//	 * 
//	 * @return value of the field
//	 */
//	public Boolean getLocalCacheEnabled(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.get_local_cache_enabled";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toBoolean(result);
//	}
//
//	/**
//	 * Get the introduced_by field of the given SR.
//	 * 
//	 * @return value of the field
//	 */
//	public DRTask getIntroducedBy(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.get_introduced_by";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toDRTask(result);
//	}

	/**
	 * Set the other_config field of the given SR.
	 * 
	 * @param otherConfig
	 *            New value to set
	 */
	public void setOtherConfig(Connection c, Map<String, String> otherConfig)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref),
				Marshalling.toXMLRPC(otherConfig) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Add the given key-value pair to the other_config field of the given SR.
	 * 
	 * @param key
	 *            Key to add
	 * @param value
	 *            Value to add
	 */
	public void addToOtherConfig(Connection c, String key, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.add_to_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key),
				Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Remove the given key and its corresponding value from the other_config
	 * field of the given SR. If the key is not in that Map, then do nothing.
	 * 
	 * @param key
	 *            Key to remove
	 */
	public void removeFromOtherConfig(Connection c, String key)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.remove_from_other_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Set the tags field of the given SR.
	 * 
	 * @param tags
	 *            New value to set
	 */
	public void setTags(Connection c, Set<String> tags)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_tags";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(tags) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Add the given value to the tags field of the given SR. If the value is
	 * already in that Set, then do nothing.
	 * 
	 * @param value
	 *            New value to add
	 */
	public void addTags(Connection c, String value) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.add_tags";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Remove the given value from the tags field of the given SR. If the value
	 * is not in that Set, then do nothing.
	 * 
	 * @param value
	 *            Value to remove
	 */
	public void removeTags(Connection c, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.remove_tags";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Set the sm_config field of the given SR.
	 * 
	 * @param smConfig
	 *            New value to set
	 */
	public void setSmConfig(Connection c, Map<String, String> smConfig)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_sm_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(smConfig) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Add the given key-value pair to the sm_config field of the given SR.
	 * 
	 * @param key
	 *            Key to add
	 * @param value
	 *            Value to add
	 */
	public void addToSmConfig(Connection c, String key, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.add_to_sm_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key),
				Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Remove the given key and its corresponding value from the sm_config field
	 * of the given SR. If the key is not in that Map, then do nothing.
	 * 
	 * @param key
	 *            Key to remove
	 */
	public void removeFromSmConfig(Connection c, String key)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.remove_from_sm_config";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(key) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

//	/**
//	 * Create a new Storage Repository and introduce it into the managed system,
//	 * creating both SR record and PBD record to attach it to current host (with
//	 * specified device_config parameters)
//	 * 
//	 * @param host
//	 *            The host to create/make the SR on
//	 * @param deviceConfig
//	 *            The device config string that will be passed to backend SR
//	 *            driver
//	 * @param physicalSize
//	 *            The physical size of the new storage repository
//	 * @param nameLabel
//	 *            The name of the new storage repository
//	 * @param nameDescription
//	 *            The description of the new storage repository
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param contentType
//	 *            The type of the new SRs content, if required (e.g. ISOs)
//	 * @param shared
//	 *            True if the SR (is capable of) being shared by multiple hosts
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return Task
//	 */
//	public static Task createAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, VersionException, XenAPIException,
//			XmlRpcException, Types.SrUnknownDriver {
//
//		if (c.rioConnection) {
//			if (smConfig.isEmpty()) {
//				return rioCreateAsync(c, host, deviceConfig, physicalSize,
//						nameLabel, nameDescription, type, contentType, shared);
//			} else {
//				throw new Types.VersionException(
//						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
//			}
//		} else {
//			return miamiCreateAsync(c, host, deviceConfig, physicalSize,
//					nameLabel, nameDescription, type, contentType, shared,
//					smConfig);
//		}
//	}
//
//	private static Task rioCreateAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared) throws BadServerResponse,
//			XmlRpcException, XenAPIException, Types.SrUnknownDriver {
//		String method_call = "Async.SR.create";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	private static Task miamiCreateAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, XmlRpcException, XenAPIException,
//			Types.SrUnknownDriver {
//		String method_call = "Async.SR.create";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared), Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Create a new Storage Repository and introduce it into the managed system,
	 * creating both SR record and PBD record to attach it to current host (with
	 * specified device_config parameters)
	 * 
	 * @param host
	 *            The host to create/make the SR on
	 * @param deviceConfig
	 *            The device config string that will be passed to backend SR
	 *            driver
	 * @param physicalSize
	 *            The physical size of the new storage repository
	 * @param nameLabel
	 *            The name of the new storage repository
	 * @param nameDescription
	 *            The description of the new storage repository
	 * @param type
	 *            The type of the SR; used to specify the SR backend driver to
	 *            use
	 * @param contentType
	 *            The type of the new SRs content, if required (e.g. ISOs)
	 * @param shared
	 *            True if the SR (is capable of) being shared by multiple hosts
	 * @param smConfig
	 *            Storage backend specific configuration options
	 * @return The reference of the newly created Storage Repository.
	 */
	public static SR create(Connection c, Host host,
			Map<String, String> deviceConfig, Long physicalSize,
			String nameLabel, String nameDescription, String type,
			String contentType, Boolean shared, Map<String, String> smConfig)
			throws BadServerResponse, VersionException, XenAPIException,
			XmlRpcException, Types.SrUnknownDriver {

		if (c.rioConnection) {
			if (smConfig.isEmpty()) {
				return rioCreate(c, host, deviceConfig, physicalSize,
						nameLabel, nameDescription, type, contentType, shared);
			} else {
				throw new Types.VersionException(
						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
			}
		} else {
			return miamiCreate(c, host, deviceConfig, physicalSize, nameLabel,
					nameDescription, type, contentType, shared, smConfig);
		}
	}
	
	public static SR create4Test(Connection c, String name, String target) throws XenAPIException, XmlRpcException
	{
		String method_call = "SR.create";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(name),
				Marshalling.toXMLRPC(target)};
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSR(result);	
	}

	public static void delete4Test(Connection c, String poolName) throws XenAPIException, XmlRpcException
	{
		String method_call = "SR.delete";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(poolName)};
		c.dispatch(method_call, method_params);
		return ;	
	}
	
	public static String list4Test(Connection c) throws XenAPIException, XmlRpcException
	{
		String method_call = "SR.list_all";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				};
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);	
	}
	
	private static SR rioCreate(Connection c, Host host,
			Map<String, String> deviceConfig, Long physicalSize,
			String nameLabel, String nameDescription, String type,
			String contentType, Boolean shared) throws BadServerResponse,
			XmlRpcException, XenAPIException, Types.SrUnknownDriver {
		String method_call = "SR.create";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
				Marshalling.toXMLRPC(physicalSize),
				Marshalling.toXMLRPC(nameLabel),
				Marshalling.toXMLRPC(nameDescription),
				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
				Marshalling.toXMLRPC(shared) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSR(result);
	}

	private static SR miamiCreate(Connection c, Host host,
			Map<String, String> deviceConfig, Long physicalSize,
			String nameLabel, String nameDescription, String type,
			String contentType, Boolean shared, Map<String, String> smConfig)
			throws BadServerResponse, XmlRpcException, XenAPIException,
			Types.SrUnknownDriver {
		String method_call = "SR.create";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
				Marshalling.toXMLRPC(physicalSize),
				Marshalling.toXMLRPC(nameLabel),
				Marshalling.toXMLRPC(nameDescription),
				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
				Marshalling.toXMLRPC(shared), Marshalling.toXMLRPC(smConfig) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSR(result);
	}
//
//	/**
//	 * Introduce a new Storage Repository into the managed system
//	 * 
//	 * @param uuid
//	 *            The uuid assigned to the introduced SR
//	 * @param nameLabel
//	 *            The name of the new storage repository
//	 * @param nameDescription
//	 *            The description of the new storage repository
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param contentType
//	 *            The type of the new SRs content, if required (e.g. ISOs)
//	 * @param shared
//	 *            True if the SR (is capable of) being shared by multiple hosts
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return Task
//	 */
//	public static Task introduceAsync(Connection c, String uuid,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, VersionException, XenAPIException,
//			XmlRpcException {
//
//		if (c.rioConnection) {
//			if (smConfig.isEmpty()) {
//				return rioIntroduceAsync(c, uuid, nameLabel, nameDescription,
//						type, contentType, shared);
//			} else {
//				throw new Types.VersionException(
//						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
//			}
//		} else {
//			return miamiIntroduceAsync(c, uuid, nameLabel, nameDescription,
//					type, contentType, shared, smConfig);
//		}
//	}
//
//	private static Task rioIntroduceAsync(Connection c, String uuid,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared) throws BadServerResponse,
//			XmlRpcException, XenAPIException {
//		String method_call = "Async.SR.introduce";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(uuid), Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	private static Task miamiIntroduceAsync(Connection c, String uuid,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, XmlRpcException, XenAPIException {
//		String method_call = "Async.SR.introduce";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(uuid), Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared), Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//	 * Introduce a new Storage Repository into the managed system
//	 * 
//	 * @param uuid
//	 *            The uuid assigned to the introduced SR
//	 * @param nameLabel
//	 *            The name of the new storage repository
//	 * @param nameDescription
//	 *            The description of the new storage repository
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param contentType
//	 *            The type of the new SRs content, if required (e.g. ISOs)
//	 * @param shared
//	 *            True if the SR (is capable of) being shared by multiple hosts
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return The reference of the newly introduced Storage Repository.
//	 */
//	public static SR introduce(Connection c, String uuid, String nameLabel,
//			String nameDescription, String type, String contentType,
//			Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, VersionException, XenAPIException,
//			XmlRpcException {
//
//		if (c.rioConnection) {
//			if (smConfig.isEmpty()) {
//				return rioIntroduce(c, uuid, nameLabel, nameDescription, type,
//						contentType, shared);
//			} else {
//				throw new Types.VersionException(
//						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
//			}
//		} else {
//			return miamiIntroduce(c, uuid, nameLabel, nameDescription, type,
//					contentType, shared, smConfig);
//		}
//	}
//
//	private static SR rioIntroduce(Connection c, String uuid, String nameLabel,
//			String nameDescription, String type, String contentType,
//			Boolean shared) throws BadServerResponse, XmlRpcException,
//			XenAPIException {
//		String method_call = "SR.introduce";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(uuid), Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toSR(result);
//	}
//
//	private static SR miamiIntroduce(Connection c, String uuid,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Boolean shared, Map<String, String> smConfig)
//			throws BadServerResponse, XmlRpcException, XenAPIException {
//		String method_call = "SR.introduce";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(uuid), Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(shared), Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toSR(result);
//	}
//
//	/**
//	 * Create a new Storage Repository on disk. This call is deprecated: use
//	 * SR.create instead.
//	 * 
//	 * @deprecated
//	 * 
//	 * @param host
//	 *            The host to create/make the SR on
//	 * @param deviceConfig
//	 *            The device config string that will be passed to backend SR
//	 *            driver
//	 * @param physicalSize
//	 *            The physical size of the new storage repository
//	 * @param nameLabel
//	 *            The name of the new storage repository
//	 * @param nameDescription
//	 *            The description of the new storage repository
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param contentType
//	 *            The type of the new SRs content, if required (e.g. ISOs)
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return Task
//	 */
//	@Deprecated
//	public static Task makeAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Map<String, String> smConfig)
//			throws BadServerResponse, VersionException, XenAPIException,
//			XmlRpcException {
//
//		if (c.rioConnection) {
//			if (smConfig.isEmpty()) {
//				return rioMakeAsync(c, host, deviceConfig, physicalSize,
//						nameLabel, nameDescription, type, contentType);
//			} else {
//				throw new Types.VersionException(
//						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
//			}
//		} else {
//			return miamiMakeAsync(c, host, deviceConfig, physicalSize,
//					nameLabel, nameDescription, type, contentType, smConfig);
//		}
//	}
//
//	@Deprecated
//	private static Task rioMakeAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType) throws BadServerResponse, XmlRpcException,
//			XenAPIException {
//		String method_call = "Async.SR.make";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	@Deprecated
//	private static Task miamiMakeAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Map<String, String> smConfig)
//			throws BadServerResponse, XmlRpcException, XenAPIException {
//		String method_call = "Async.SR.make";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//	 * Create a new Storage Repository on disk. This call is deprecated: use
//	 * SR.create instead.
//	 * 
//	 * @deprecated
//	 * 
//	 * @param host
//	 *            The host to create/make the SR on
//	 * @param deviceConfig
//	 *            The device config string that will be passed to backend SR
//	 *            driver
//	 * @param physicalSize
//	 *            The physical size of the new storage repository
//	 * @param nameLabel
//	 *            The name of the new storage repository
//	 * @param nameDescription
//	 *            The description of the new storage repository
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param contentType
//	 *            The type of the new SRs content, if required (e.g. ISOs)
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return The uuid of the newly created Storage Repository.
//	 */
//	@Deprecated
//	public static String make(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Map<String, String> smConfig)
//			throws BadServerResponse, VersionException, XenAPIException,
//			XmlRpcException {
//
//		if (c.rioConnection) {
//			if (smConfig.isEmpty()) {
//				return rioMake(c, host, deviceConfig, physicalSize, nameLabel,
//						nameDescription, type, contentType);
//			} else {
//				throw new Types.VersionException(
//						"smConfig parameter must be empty map for Rio (legacy XenServer) host");
//			}
//		} else {
//			return miamiMake(c, host, deviceConfig, physicalSize, nameLabel,
//					nameDescription, type, contentType, smConfig);
//		}
//	}
//
//	@Deprecated
//	private static String rioMake(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType) throws BadServerResponse, XmlRpcException,
//			XenAPIException {
//		String method_call = "SR.make";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toString(result);
//	}
//
//	@Deprecated
//	private static String miamiMake(Connection c, Host host,
//			Map<String, String> deviceConfig, Long physicalSize,
//			String nameLabel, String nameDescription, String type,
//			String contentType, Map<String, String> smConfig)
//			throws BadServerResponse, XmlRpcException, XenAPIException {
//		String method_call = "SR.make";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(physicalSize),
//				Marshalling.toXMLRPC(nameLabel),
//				Marshalling.toXMLRPC(nameDescription),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(contentType),
//				Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toString(result);
//	}
//
//	/**
//	 * Destroy specified SR, removing SR-record from database and remove SR from
//	 * disk. (In order to affect this operation the appropriate device_config is
//	 * read from the specified SR's PBD on current host)
//	 * 
//	 * @return Task
//	 */
//	public Task destroyAsync(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException, Types.SrHasPbd {
//		String method_call = "Async.SR.destroy";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Destroy specified SR, removing SR-record from database and remove SR from
	 * disk. (In order to affect this operation the appropriate device_config is
	 * read from the specified SR's PBD on current host)
	 * 
	 */
	public void destroy(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException, Types.SrHasPbd {
		String method_call = "SR.destroy";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

//	/**
//	 * Removing specified SR-record from database, without attempting to remove
//	 * SR from disk
//	 * 
//	 * @return Task
//	 */
//	public Task forgetAsync(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException, Types.SrHasPbd {
//		String method_call = "Async.SR.forget";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Removing specified SR-record from database, without attempting to remove
	 * SR from disk
	 * 
	 */
	// public void forget(Connection c) throws BadServerResponse,
	// XenAPIException,
	// XmlRpcException, Types.SrHasPbd {
	// String method_call = "SR.forget";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Refresh the fields on the SR object
//	 * 
//	 * @return Task
//	 */
//	public Task updateAsync(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.update";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Refresh the fields on the SR object
	 * 
	 */
	public void update(Connection c) throws BadServerResponse, XenAPIException,
			XmlRpcException {
		String method_call = "SR.update";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Return a set of all the SR types supported by the system
	 * 
	 * @return the supported SR types
	 */
	// public static Set<String> getSupportedTypes(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.get_supported_types";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session) };
	// Map response = c.dispatch(method_call, method_params);
	// Object result = response.get("Value");
	// return Types.toSetOfString(result);
	// }

//	/**
//	 * Refreshes the list of VDIs associated with an SR
//	 * 
//	 * @return Task
//	 */
//	public Task scanAsync(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.scan";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Refreshes the list of VDIs associated with an SR
	 * 
	 */
	// public void scan(Connection c) throws BadServerResponse, XenAPIException,
	// XmlRpcException {
	// String method_call = "SR.scan";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	// frank
//	public void mount(Connection c) throws BadServerResponse, XenAPIException,
//			XmlRpcException {
//		String method_call = "SR.mount";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}
//
//	public static void mountAll(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.mount_all";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}
//
//	public void umount(Connection c) throws BadServerResponse, XenAPIException,
//			XmlRpcException {
//		String method_call = "SR.umount";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}
//
//	public static void umountAll(Connection c) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "SR.umount_all";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}

//	/**
//	 * Perform a backend-specific scan, using the given device_config. If the
//	 * device_config is complete, then this will return a list of the SRs
//	 * present of this type on the device, if any. If the device_config is
//	 * partial, then a backend-specific scan will be performed, returning
//	 * results that will guide the user in improving the device_config.
//	 * 
//	 * @param host
//	 *            The host to create/make the SR on
//	 * @param deviceConfig
//	 *            The device config string that will be passed to backend SR
//	 *            driver
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return Task
//	 */
//	public static Task probeAsync(Connection c, Host host,
//			Map<String, String> deviceConfig, String type,
//			Map<String, String> smConfig) throws BadServerResponse,
//			XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.probe";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
//				Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(smConfig) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

//	/**
//	 * Perform a backend-specific scan, using the given device_config. If the
//	 * device_config is complete, then this will return a list of the SRs
//	 * present of this type on the device, if any. If the device_config is
//	 * partial, then a backend-specific scan will be performed, returning
//	 * results that will guide the user in improving the device_config.
//	 * 
//	 * @param host
//	 *            The host to create/make the SR on
//	 * @param deviceConfig
//	 *            The device config string that will be passed to backend SR
//	 *            driver
//	 * @param type
//	 *            The type of the SR; used to specify the SR backend driver to
//	 *            use
//	 * @param smConfig
//	 *            Storage backend specific configuration options
//	 * @return An XML fragment containing the scan results. These are specific
//	 *         to the scan being performed, and the backend.
//	 */
	// public static String probe(Connection c, Host host,
	// Map<String, String> deviceConfig, String type,
	// Map<String, String> smConfig) throws BadServerResponse,
	// XenAPIException, XmlRpcException {
	// String method_call = "SR.probe";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(host), Marshalling.toXMLRPC(deviceConfig),
	// Marshalling.toXMLRPC(type), Marshalling.toXMLRPC(smConfig) };
	// Map response = c.dispatch(method_call, method_params);
	// Object result = response.get("Value");
	// return Types.toString(result);
	// }

//	/**
//	 * Sets the shared flag on the SR
//	 * 
//	 * @param value
//	 *            True if the SR is shared
//	 * @return Task
//	 */
//	public Task setSharedAsync(Connection c, Boolean value)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.set_shared";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Sets the shared flag on the SR
	 * 
	 * @param value
	 *            True if the SR is shared
	 */
	// public void setShared(Connection c, Boolean value)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.set_shared";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Set the name label of the SR
//	 * 
//	 * @param value
//	 *            The name label for the SR
//	 * @return Task
//	 */
//	public Task setNameLabelAsync(Connection c, String value)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.set_name_label";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Set the name label of the SR
	 * 
	 * @param value
	 *            The name label for the SR
	 */
	public void setNameLabel(Connection c, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_name_label";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

//	/**
//	 * Set the name description of the SR
//	 * 
//	 * @param value
//	 *            The name description for the SR
//	 * @return Task
//	 */
//	public Task setNameDescriptionAsync(Connection c, String value)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.set_name_description";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
	 * Set the name description of the SR
	 * 
	 * @param value
	 *            The name description for the SR
	 */
	public void setNameDescription(Connection c, String value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_name_description";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

//	/**
//	 * Create a placeholder for a named binary blob of data that is associated
//	 * with this SR
//	 * 
//	 * @param name
//	 *            The name associated with the blob
//	 * @param mimeType
//	 *            The mime type for the data. Empty string translates to
//	 *            application/octet-stream
//	 * @return Task
//	 */
//	public Task createNewBlobAsync(Connection c, String name, String mimeType)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.create_new_blob";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(name),
//				Marshalling.toXMLRPC(mimeType) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//	 * Create a placeholder for a named binary blob of data that is associated
//	 * with this SR
//	 * 
//	 * @param name
//	 *            The name associated with the blob
//	 * @param mimeType
//	 *            The mime type for the data. Empty string translates to
//	 *            application/octet-stream
//	 * @return The reference of the blob, needed for populating its data
//	 */
	// public Blob createNewBlob(Connection c, String name, String mimeType)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.create_new_blob";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(name),
	// Marshalling.toXMLRPC(mimeType) };
	// Map response = c.dispatch(method_call, method_params);
	// Object result = response.get("Value");
	// return Types.toBlob(result);
	// }

//	/**
//	 * Sets the SR's physical_size field
//	 * 
//	 * @param value
//	 *            The new value of the SR's physical_size
//	 */
	// public void setPhysicalSize(Connection c, Long value)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.set_physical_size";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Sets the SR's virtual_allocation field
//	 * 
//	 * @param value
//	 *            The new value of the SR's virtual_allocation
//	 */
	// public void setVirtualAllocation(Connection c, Long value)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.set_virtual_allocation";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Sets the SR's physical_utilisation field
//	 * 
//	 * @param value
//	 *            The new value of the SR's physical utilisation
//	 */
	// public void setPhysicalUtilisation(Connection c, Long value)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.set_physical_utilisation";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Returns successfully if the given SR can host an HA statefile. Otherwise
//	 * returns an error to explain why not
//	 * 
//	 * @return Task
//	 */
//	public Task assertCanHostHaStatefileAsync(Connection c)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.assert_can_host_ha_statefile";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//	 * Returns successfully if the given SR can host an HA statefile. Otherwise
//	 * returns an error to explain why not
//	 * 
//	 */
	// public void assertCanHostHaStatefile(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.assert_can_host_ha_statefile";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * Returns successfully if the given SR supports database replication.
//	 * Otherwise returns an error to explain why not.
//	 * 
//	 * @return Task
//	 */
//	public Task assertSupportsDatabaseReplicationAsync(Connection c)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.assert_supports_database_replication";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//	 * Returns successfully if the given SR supports database replication.
//	 * Otherwise returns an error to explain why not.
//	 * 
//	 */
	// public void assertSupportsDatabaseReplication(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.assert_supports_database_replication";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * 
//	 * 
//	 * @return Task
//	 */
//	public Task enableDatabaseReplicationAsync(Connection c)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.enable_database_replication";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}
//
//	/**
//     * 
//     *
//     */
	// public void enableDatabaseReplication(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.enable_database_replication";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

//	/**
//	 * 
//	 * 
//	 * @return Task
//	 */
//	public Task disableDatabaseReplicationAsync(Connection c)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "Async.SR.disable_database_replication";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(this.ref) };
//		Map response = c.dispatch(method_call, method_params);
//		Object result = response.get("Value");
//		return Types.toTask(result);
//	}

	/**
     * 
     *
     */
	// public void disableDatabaseReplication(Connection c)
	// throws BadServerResponse, XenAPIException, XmlRpcException {
	// String method_call = "SR.disable_database_replication";
	// String session = c.getSessionReference();
	// Object[] method_params = { Marshalling.toXMLRPC(session),
	// Marshalling.toXMLRPC(this.ref) };
	// Map response = c.dispatch(method_call, method_params);
	// return;
	// }

	/**
	 * Return a list of all the SRs known to the system.
	 * 
	 * @return references to all objects
	 */
	public static Set<SR> getAll(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_all";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfSR(result);
	}

	/**
	 * Return a map of SR references to SR records for all SRs known to the
	 * system.
	 * 
	 * @return records of all objects
	 */
	public static Map<SR, SR.Record> getAllRecords(Connection c)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.get_all_records";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toMapOfSRSRRecord(result);
	}

	/**
	 * Return Set<SR> of SR crushed down.
	 * 
	 * @author Frank
	 * @return Set<SR>
	 */
	public static Set<SR> checkZfsValid(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.check_zfs_valid";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfSR(result);
	}

	/**
	 * Get the state field of the given SR.
	 * 
	 * @return value of the field
	 */
	public String getState(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_state";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Set the name description of the SR
	 * 
	 * @param value
	 *            The state for the SR
	 */
	public void setState(Connection c, String value) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.set_state";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

//	/**
//	 * Change Zpool SR attached ip address.
//	 * 
//	 * @author Frank
//	 */
//	public static void setZpoolHostIp(Connection c, String location, Host host)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "SR.set_zpool_host_ip";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(location), Marshalling.toXMLRPC(host) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}
//
//	/**
//	 * Change Zpool SR attached ip address.
//	 * 
//	 * @author Frank
//	 */
//	public static void setZpoolIp(Connection c, String location, String ip)
//			throws BadServerResponse, XenAPIException, XmlRpcException {
//		String method_call = "SR.set_zpool_ip";
//		String session = c.getSessionReference();
//		Object[] method_params = { Marshalling.toXMLRPC(session),
//				Marshalling.toXMLRPC(location), Marshalling.toXMLRPC(ip) };
//		Map response = c.dispatch(method_call, method_params);
//		return;
//	}

	public String getLocation(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_location";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toString(result);
	}

	/**
	 * Set default SR.
	 * 
	 * @author Frank
	 */
	public void setIsDefault(Connection c, Boolean value)
			throws BadServerResponse, XenAPIException, XmlRpcException {
		String method_call = "SR.set_is_default";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref), Marshalling.toXMLRPC(value) };
		Map response = c.dispatch(method_call, method_params);
		return;
	}

	/**
	 * Get whether if is default or not.
	 * 
	 * @author Frank
	 */
	public Boolean getIsDefault(Connection c) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_is_default";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session),
				Marshalling.toXMLRPC(this.ref) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toBoolean(result);
	}
	
	/**
	 * Return a list of all the SRs known to the system as default sr.
	 * 
	 * @return references to all objects
	 */
	public static Set<SR> getByDefault(Connection c, Boolean shared) throws BadServerResponse,
			XenAPIException, XmlRpcException {
		String method_call = "SR.get_by_default";
		String session = c.getSessionReference();
		Object[] method_params = { Marshalling.toXMLRPC(session), Marshalling.toXMLRPC(shared) };
		Map response = c.dispatch(method_call, method_params);
		Object result = response.get("Value");
		return Types.toSetOfSR(result);
	}
	
	//获取ISO列表
		//ISOList  [ { isoid:xxxx, isoname:xxxx } , {....} ]
		public static JSONArray getISOs(Connection connection, String uuid)throws BadServerResponse,
		XenAPIException, XmlRpcException {
			JSONArray ja = new JSONArray();
			SR sr = SR.getByUuid(connection, uuid);
			for(VDI vdi : sr.getVDIs(connection)){
				VDI.Record record = vdi.getRecord(connection, false);
				String isoUuid = record.uuid;
				String isoName = record.nameLabel;
				JSONObject jo = new JSONObject();
				jo.put("isoid", isoUuid);
				jo.put("isoname", isoName);
				ja.put(jo);
			}
		return ja;	
			
		}
	
}