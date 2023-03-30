package org.hibernate.bugs;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class EntityBChildTwo extends AbstractEntityB {

	@Column(name = "ACTION")
	@Type(ChildTwoActionUserType.class)
	private ChildTwoAction action;

	public ChildTwoAction getAction() {
		return action;
	}

	public void setAction(ChildTwoAction action) {
		this.action = action;
	}

	static enum ChildTwoAction {
		ACTION_10, ACTION_20
	}

	public static class ChildTwoActionUserType extends AbstractUserType<ChildTwoAction> {

		public ChildTwoActionUserType() {
			super(ChildTwoAction.class);
		}
	}
}