package telran.util;
import telran.util.Collection;
public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;
	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry(key, null));   
		
		return entry == null ? null : entry.getValue();
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = set.get(new Entry(key, null)); 
		V res = null;
		if(entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value)); 
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		
		if(key == null) {
			throw new NullPointerException();
		}
		Entry<K, V> entry = set.get(new Entry(key, null)); 
		
		return entry == null ? false : true;
	}

	@Override
	public boolean containsValue(V value) {
		if(value == null) {
			throw new NullPointerException();
		}
		// one way
//		return entrySet().stream().filter(n -> n.getValue()
//				.equals(value)).toArray().length != 0 ? true : false;
		
		//other way more effective:
		return entrySet().stream().anyMatch(k -> k.getValue().equals(value));
	}

	@Override
	public Set<K> keySet() {	
		Set<K> res = getKeySet();
		set.stream().map(e -> e.getKey()).forEach(key -> res.add(key));
		return res; 
	}

	abstract protected Set<K> getKeySet();
	
	@Override
	public Collection<V> values() {
//		Collection<V> res = new ArrayList<>();
//		// TODO Auto-generated method stub
//		
//		set.stream().map(e -> e.getValue()).forEach(v -> res.add(v));
//		
		return null;
	}

		

	@Override
	public Set<Entry<K, V>> entrySet() {
		
		return set;
	}
	
	@Override
	public V remove(K key) {
		
		V res = get(key);
		entrySet().removeIf(k -> k.getKey().equals(key));
				
		return res ;
	}
}
