package org.openstack4j.openstack.compute.domain;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Addresses;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class NovaAddresses implements Addresses {

	private static final long serialVersionUID = 1L;

	@JsonProperty("addresses")
	private Map<String, List<NovaAddress>> addresses = Maps.newHashMap();
	
	@Override
	public void add(String key, Address value) {
		if (!addresses.containsKey(key))
			addresses.put(key, Lists.<NovaAddress>newArrayList());
		
		addresses.get(key).add((NovaAddress) value);
	}
	
	@JsonAnySetter
	public void add(String key, List<NovaAddress> value) {
		addresses.put(key, value);
	}

	@Override
	public Map<String, List<? extends Address>> getAddresses() {
		return encapsulate();
	}

	@Override
	public List<? extends Address> getAddresses(String type) {
		return addresses.get(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
						.add("addresses", addresses).addValue("\n")
						.toString();
	}
	
	@SuppressWarnings("unchecked")
	private <T> T encapsulate() {
		return (T) addresses;
	}
	
	public static class NovaAddress implements Address {

		private static final long serialVersionUID = 1L;
		@JsonProperty("OS-EXT-IPS-MAC:mac_addr")
		private String macAddr;
		private int version;
		private String addr;
		@JsonProperty("OS-EXT-IPS:type")
		private String type;
		
		@Override
		public String getMacAddr() {
			return macAddr;
		}

		@Override
		public int getVersion() {
			return version;
		}

		@Override
		public String getAddr() {
			return addr;
		}

		@Override
		public String getType() {
			return type;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return Objects.toStringHelper(this).omitNullValues()
							.add("address", addr).add("type", type).add("version", version)
							.add("macaddr", macAddr).addValue("\n")
							.toString();
		}
		
	}
	
}
