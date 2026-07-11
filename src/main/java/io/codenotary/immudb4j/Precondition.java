package io.codenotary.immudb4j;

import com.google.protobuf.ByteString;
import io.codenotary.immudb.ImmudbProto;

public interface Precondition {

    byte[] getKey();

    ImmudbProto.Precondition toProto();

    class KeyMustExistPrecondition implements Precondition {
        private final byte[] key;

        private KeyMustExistPrecondition(byte[] key) {
            this.key = key;
        }

        public static KeyMustExistPrecondition of(byte[] key) {
            return new KeyMustExistPrecondition(key);
        }

        public static KeyMustExistPrecondition of(String key) {
            return new KeyMustExistPrecondition(Utils.toByteArray(key));
        }

        @Override
        public byte[] getKey() {
            return key;
        }

        @Override
        public ImmudbProto.Precondition toProto() {
            return ImmudbProto.Precondition.newBuilder()
                    .setKeyMustExist(ImmudbProto.Precondition.KeyMustExistPrecondition.newBuilder()
                            .setKey(ByteString.copyFrom(this.key)))
                    .build();
        }
    }

    class KeyMustNotExistPrecondition implements Precondition {
        private final byte[] key;

        private KeyMustNotExistPrecondition(byte[] key) {
            this.key = key;
        }

        public static KeyMustNotExistPrecondition of(byte[] key) {
            return new KeyMustNotExistPrecondition(key);
        }

        public static KeyMustNotExistPrecondition of(String key) {
            return new KeyMustNotExistPrecondition(Utils.toByteArray(key));
        }

        @Override
        public byte[] getKey() {
            return this.key;
        }

        @Override
        public ImmudbProto.Precondition toProto() {
            return ImmudbProto.Precondition.newBuilder()
                    .setKeyMustNotExist(ImmudbProto.Precondition.KeyMustNotExistPrecondition.newBuilder()
                            .setKey(ByteString.copyFrom(this.key)))
                    .build();
        }
    }

    class KeyNotModifiedAfterTXPrecondition implements Precondition {

        private final byte[] key;
        private final long txID;

        private KeyNotModifiedAfterTXPrecondition(byte[] key, long txID) {
            this.key = key;
            this.txID = txID;
        }

        public static KeyNotModifiedAfterTXPrecondition of(byte[] key, long txID) {
            return new KeyNotModifiedAfterTXPrecondition(key, txID);
        }

        public static KeyNotModifiedAfterTXPrecondition of(String key, long txID) {
            return new KeyNotModifiedAfterTXPrecondition(Utils.toByteArray(key), txID);
        }

        public long getTxID() {
            return txID;
        }

        @Override
        public byte[] getKey() {
            return this.key;
        }

        @Override
        public ImmudbProto.Precondition toProto() {
            return ImmudbProto.Precondition.newBuilder()
                    .setKeyNotModifiedAfterTX(ImmudbProto.Precondition.KeyNotModifiedAfterTXPrecondition.newBuilder()
                            .setKey(ByteString.copyFrom(this.key))
                            .setTxID(this.txID)
                    )
                    .build();
        }
    }
}
