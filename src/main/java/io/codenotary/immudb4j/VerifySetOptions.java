package io.codenotary.immudb4j;

import java.util.Collections;
import java.util.List;

public class VerifySetOptions {
    private final byte[] key;
    private final byte[] value;
    private final List<Precondition> preconditions;

    private VerifySetOptions(Builder builder) {
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

    public List<Precondition> getPreconditions() {
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
        private List<Precondition> preconditions;

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

        public Builder withPreconditions(List<Precondition> preconditions) {
            this.preconditions = preconditions;
            return this;
        }

        public VerifySetOptions build() {
            return new VerifySetOptions(this);
        }
    }

}
