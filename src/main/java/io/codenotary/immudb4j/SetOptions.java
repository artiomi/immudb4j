/*
Copyright 2022 CodeNotary, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.codenotary.immudb4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SetOptions {
    private final byte[] key;
    private final byte[] value;
    private final List<Precondition> preconditions;

    private SetOptions(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.preconditions = Collections.unmodifiableList(builder.preconditions);
    }

    public static Builder newBuilder() {
        return new Builder();
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
            Objects.requireNonNull(key, "'key' can't be null");
            this.key = key;
            return this;
        }

        public Builder withKey(String key) {
            Objects.requireNonNull(key, "'key' can't be null");
            this.key = Utils.toByteArray(key);
            return this;
        }

        public Builder withValue(byte[] value) {
            this.value = value;
            return this;
        }

        public Builder withValue(String value) {
            this.value = Utils.toByteArray(value);
            return this;
        }

        public Builder withPreconditions(List<Precondition> preconditions) {
            this.preconditions = preconditions == null ? Collections.emptyList() : preconditions;
            return this;
        }

        public SetOptions build() {
            Objects.requireNonNull(this.key, "'key' can't be null");
            return new SetOptions(this);
        }
    }

}
