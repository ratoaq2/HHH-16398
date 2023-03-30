package org.hibernate.bugs;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

abstract class AbstractUserType<T extends Enum<T>> implements UserType<T> {

	private Class<T> type;

	AbstractUserType(Class<T> type) {
		this.type = type;
	}

	@Override
	public int getSqlType() {
		return SqlTypes.INTEGER;
	}

	@Override
	public Class<T> returnedClass() {
		return type;
	}

	@Override
	public boolean equals(T x, T y) {
		return Objects.equals(x, y);
	}

	@Override
	public int hashCode(T x) {
		return Objects.hashCode(x);
	}

	@Override
	public T nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		return rs.wasNull() ? null : type.getEnumConstants()[rs.getInt(position)];
	}

	@Override
	public void nullSafeSet(PreparedStatement st, T value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, getSqlType());
		} else {
			st.setInt(index, value.ordinal());
		}
	}

	@Override
	public T deepCopy(T value) {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(T value) {
		return value;
	}

	@Override
	public T assemble(Serializable cached, Object owner) {
		return (T) cached;
	}

}