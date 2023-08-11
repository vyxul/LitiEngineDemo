
public abstract class Component implements Exportable {

	private final Entity entity;
	private final ComponentType type;

	public Component(Entity entity, ComponentType type) {
		this.entity = entity;
		this.type = type;
	}

	public Entity getEntity() {
		return entity;
	}



	public ComponentType getType() {
		return type;
	}

	protected void update(int level) {
	}


	protected void remove() {
	}

}
