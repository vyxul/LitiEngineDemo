import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Entity implements Exportable 
{

	public final int id;

	private boolean manifested = false;
	private Map<ComponentType, Component> components;
	private List<Component> activeComponents = new ArrayList<>();
    
	public Entity() 
	{
		this.id = 1;
	}
	
	public Entity manifest() 
	{
		if(manifested)
			return this;
		this.manifested = true;
		return this;
	}
	
	public void remove() 
	{
		if(!manifested)
			return;
		this.manifested = false;
	}

	public boolean isManifested() 
	{
		return manifested;
	}
	
	public void setComponents(Map<ComponentType, Component> components) 
	{
		this.components = components;
		getActiveComponents();
	}

	protected Map<ComponentType, Component> getComponents() 
	{
		return components;
	}

	public Component getComponent(ComponentType type) 
	{
		return components.get(type);
	}



	public void update(int updateLevel) 
	{
		for (Component component : activeComponents) {
			component.update(updateLevel);
		}
	}

	private void getActiveComponents() 
	{
		for (Component component : components.values()) {
			if (component.getType().isActive()) {
				activeComponents.add(component);
			}
		}
	}
	

		public static Entity load() throws Exception 
		{
			Entity entity = new Entity();
			entity.manifest();
			return entity;
		}

		@Override
		public String getSerializer() {
			// TODO Auto-generated method stub
			return null;
		}


}


