package io.codenotary.immudb4j;

import io.codenotary.immudb.ImmudbProto;

import java.util.Collections;
import java.util.List;

public class QueryOptions {
    private final byte[] key;
    private final byte[] value;
    private final List<ImmudbProto.Precondition> preconditions;

    private QueryOptions(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.preconditions = Collections.unmodifiableList(builder.preconditions);
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getValue() {
        return value;
    }

    public List<ImmudbProto.Precondition> getPreconditions() {
        return preconditions;
    }

    public boolean havePreconditions() {
        return preconditions != null && !preconditions.isEmpty();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private byte[] key;
        private byte[] value;
        private List<ImmudbProto.Precondition> preconditions;

        private Builder() {
            key = null;
            value = null;
            preconditions = Collections.emptyList();
        }

        public Builder withKey(byte[] key) {
            this.key = key;
            return this;
        }

        public Builder withValue(byte[] value) {
            this.value = value;
            return this;
        }

        public Builder withPreconditions(List<ImmudbProto.Precondition> preconditions) {
            this.preconditions = preconditions;
            return this;
        }

        public QueryOptions build() {
            return new QueryOptions(this);
        }
    }

}
