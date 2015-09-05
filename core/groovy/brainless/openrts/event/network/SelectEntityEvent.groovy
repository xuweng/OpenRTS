package brainless.openrts.event.network;

import groovy.transform.ToString;

import com.jme3.network.serializing.Serializable;

@Serializable
@ToString
class SelectEntityEvent extends NetworkEvent {

	int unitId;
	
	public SelectEntityEvent(){
		
	}
	
	public SelectEntityEvent(int entityId) {
		this.unitId = entityId
	}

}
