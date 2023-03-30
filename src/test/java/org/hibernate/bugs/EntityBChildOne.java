package org.hibernate.bugs;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class EntityBChildOne extends AbstractEntityB {

	@Column(name = "ACTION")
	@Type(ChildOneActionUserType.class)
	private ChildOneAction action;

	public ChildOneAction getAction() {
		return action;
	}

	public void setAction(ChildOneAction action) {
		this.action = action;
	}

	public static enum ChildOneAction {
		ACTION_1, ACTION_2
	}

	public static class ChildOneActionUserType extends AbstractUserType<ChildOneAction> {

		public ChildOneActionUserType() {
			super(ChildOneAction.class);
		}
	}
}