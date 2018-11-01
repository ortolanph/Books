package org.books.beans;

import java.util.Objects;

public class Owner {

    private int id;

    private String name;

    private String password;

    private String uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
            Objects.equals(name, owner.name) &&
            Objects.equals(password, owner.password) &&
            Objects.equals(uuid, owner.uuid);
    }

    @Override public int hashCode() {

        return Objects.hash(id, name, password, uuid);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Owner{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
