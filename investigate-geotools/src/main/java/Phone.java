// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: phone.proto

public final class Phone {
  private Phone() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ResultOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string phone_mac = 1;
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    boolean hasPhoneMac();
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    String getPhoneMac();
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    com.google.protobuf.ByteString
        getPhoneMacBytes();

    // required uint32 stamp = 2;
    /**
     * <code>required uint32 stamp = 2;</code>
     *
     * <pre>
     *time stamp
     * </pre>
     */
    boolean hasStamp();
    /**
     * <code>required uint32 stamp = 2;</code>
     *
     * <pre>
     *time stamp
     * </pre>
     */
    int getStamp();

    // required float position_x = 3;
    /**
     * <code>required float position_x = 3;</code>
     *
     * <pre>
     *axis x
     * </pre>
     */
    boolean hasPositionX();
    /**
     * <code>required float position_x = 3;</code>
     *
     * <pre>
     *axis x
     * </pre>
     */
    float getPositionX();

    // required float position_y = 4;
    /**
     * <code>required float position_y = 4;</code>
     *
     * <pre>
     *axis y
     * </pre>
     */
    boolean hasPositionY();
    /**
     * <code>required float position_y = 4;</code>
     *
     * <pre>
     *axis y
     * </pre>
     */
    float getPositionY();

    // required uint32 floor_id = 5;
    /**
     * <code>required uint32 floor_id = 5;</code>
     *
     * <pre>
     *floorid
     * </pre>
     */
    boolean hasFloorId();
    /**
     * <code>required uint32 floor_id = 5;</code>
     *
     * <pre>
     *floorid
     * </pre>
     */
    int getFloorId();

    // required uint32 scene = 6;
    /**
     * <code>required uint32 scene = 6;</code>
     *
     * <pre>
     *scene
     * </pre>
     */
    boolean hasScene();
    /**
     * <code>required uint32 scene = 6;</code>
     *
     * <pre>
     *scene
     * </pre>
     */
    int getScene();
  }
  /**
   * Protobuf drinkType {@code Result}
   */
  public static final class Result extends
      com.google.protobuf.GeneratedMessage
      implements ResultOrBuilder {
    // Use Result.newBuilder() to construct.
    private Result(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Result(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Result defaultInstance;
    public static Result getDefaultInstance() {
      return defaultInstance;
    }

    public Result getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Result(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              phoneMac_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              stamp_ = input.readUInt32();
              break;
            }
            case 29: {
              bitField0_ |= 0x00000004;
              positionX_ = input.readFloat();
              break;
            }
            case 37: {
              bitField0_ |= 0x00000008;
              positionY_ = input.readFloat();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              floorId_ = input.readUInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              scene_ = input.readUInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Phone.internal_static_Result_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Phone.internal_static_Result_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Result.class, Builder.class);
    }

    public static com.google.protobuf.Parser<Result> PARSER =
        new com.google.protobuf.AbstractParser<Result>() {
      public Result parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Result(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<Result> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string phone_mac = 1;
    public static final int PHONE_MAC_FIELD_NUMBER = 1;
    private Object phoneMac_;
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    public boolean hasPhoneMac() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    public String getPhoneMac() {
      Object ref = phoneMac_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          phoneMac_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string phone_mac = 1;</code>
     *
     * <pre>
     *phone mac
     * </pre>
     */
    public com.google.protobuf.ByteString
        getPhoneMacBytes() {
      Object ref = phoneMac_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        phoneMac_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required uint32 stamp = 2;
    public static final int STAMP_FIELD_NUMBER = 2;
    private int stamp_;
    /**
     * <code>required uint32 stamp = 2;</code>
     *
     * <pre>
     *time stamp
     * </pre>
     */
    public boolean hasStamp() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required uint32 stamp = 2;</code>
     *
     * <pre>
     *time stamp
     * </pre>
     */
    public int getStamp() {
      return stamp_;
    }

    // required float position_x = 3;
    public static final int POSITION_X_FIELD_NUMBER = 3;
    private float positionX_;
    /**
     * <code>required float position_x = 3;</code>
     *
     * <pre>
     *axis x
     * </pre>
     */
    public boolean hasPositionX() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required float position_x = 3;</code>
     *
     * <pre>
     *axis x
     * </pre>
     */
    public float getPositionX() {
      return positionX_;
    }

    // required float position_y = 4;
    public static final int POSITION_Y_FIELD_NUMBER = 4;
    private float positionY_;
    /**
     * <code>required float position_y = 4;</code>
     *
     * <pre>
     *axis y
     * </pre>
     */
    public boolean hasPositionY() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required float position_y = 4;</code>
     *
     * <pre>
     *axis y
     * </pre>
     */
    public float getPositionY() {
      return positionY_;
    }

    // required uint32 floor_id = 5;
    public static final int FLOOR_ID_FIELD_NUMBER = 5;
    private int floorId_;
    /**
     * <code>required uint32 floor_id = 5;</code>
     *
     * <pre>
     *floorid
     * </pre>
     */
    public boolean hasFloorId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required uint32 floor_id = 5;</code>
     *
     * <pre>
     *floorid
     * </pre>
     */
    public int getFloorId() {
      return floorId_;
    }

    // required uint32 scene = 6;
    public static final int SCENE_FIELD_NUMBER = 6;
    private int scene_;
    /**
     * <code>required uint32 scene = 6;</code>
     *
     * <pre>
     *scene
     * </pre>
     */
    public boolean hasScene() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>required uint32 scene = 6;</code>
     *
     * <pre>
     *scene
     * </pre>
     */
    public int getScene() {
      return scene_;
    }

    private void initFields() {
      phoneMac_ = "";
      stamp_ = 0;
      positionX_ = 0F;
      positionY_ = 0F;
      floorId_ = 0;
      scene_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasPhoneMac()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasStamp()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPositionX()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPositionY()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFloorId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasScene()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getPhoneMacBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, stamp_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeFloat(3, positionX_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeFloat(4, positionY_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeUInt32(5, floorId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeUInt32(6, scene_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getPhoneMacBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, stamp_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(3, positionX_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(4, positionY_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(5, floorId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(6, scene_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static Result parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Result parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Result parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Result parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Result parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Result parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static Result parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static Result parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static Result parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Result parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Result prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf drinkType {@code Result}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements ResultOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Phone.internal_static_Result_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Phone.internal_static_Result_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Result.class, Builder.class);
      }

      // Construct using Phone.Result.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        phoneMac_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        stamp_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        positionX_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000004);
        positionY_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000008);
        floorId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        scene_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Phone.internal_static_Result_descriptor;
      }

      public Result getDefaultInstanceForType() {
        return Result.getDefaultInstance();
      }

      public Result build() {
        Result result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Result buildPartial() {
        Result result = new Result(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.phoneMac_ = phoneMac_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.stamp_ = stamp_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.positionX_ = positionX_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.positionY_ = positionY_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.floorId_ = floorId_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.scene_ = scene_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Result) {
          return mergeFrom((Result)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Result other) {
        if (other == Result.getDefaultInstance()) return this;
        if (other.hasPhoneMac()) {
          bitField0_ |= 0x00000001;
          phoneMac_ = other.phoneMac_;
          onChanged();
        }
        if (other.hasStamp()) {
          setStamp(other.getStamp());
        }
        if (other.hasPositionX()) {
          setPositionX(other.getPositionX());
        }
        if (other.hasPositionY()) {
          setPositionY(other.getPositionY());
        }
        if (other.hasFloorId()) {
          setFloorId(other.getFloorId());
        }
        if (other.hasScene()) {
          setScene(other.getScene());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasPhoneMac()) {
          
          return false;
        }
        if (!hasStamp()) {
          
          return false;
        }
        if (!hasPositionX()) {
          
          return false;
        }
        if (!hasPositionY()) {
          
          return false;
        }
        if (!hasFloorId()) {
          
          return false;
        }
        if (!hasScene()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Result parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Result) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string phone_mac = 1;
      private Object phoneMac_ = "";
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public boolean hasPhoneMac() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public String getPhoneMac() {
        Object ref = phoneMac_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          phoneMac_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public com.google.protobuf.ByteString
          getPhoneMacBytes() {
        Object ref = phoneMac_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          phoneMac_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public Builder setPhoneMac(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        phoneMac_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public Builder clearPhoneMac() {
        bitField0_ = (bitField0_ & ~0x00000001);
        phoneMac_ = getDefaultInstance().getPhoneMac();
        onChanged();
        return this;
      }
      /**
       * <code>required string phone_mac = 1;</code>
       *
       * <pre>
       *phone mac
       * </pre>
       */
      public Builder setPhoneMacBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        phoneMac_ = value;
        onChanged();
        return this;
      }

      // required uint32 stamp = 2;
      private int stamp_ ;
      /**
       * <code>required uint32 stamp = 2;</code>
       *
       * <pre>
       *time stamp
       * </pre>
       */
      public boolean hasStamp() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required uint32 stamp = 2;</code>
       *
       * <pre>
       *time stamp
       * </pre>
       */
      public int getStamp() {
        return stamp_;
      }
      /**
       * <code>required uint32 stamp = 2;</code>
       *
       * <pre>
       *time stamp
       * </pre>
       */
      public Builder setStamp(int value) {
        bitField0_ |= 0x00000002;
        stamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 stamp = 2;</code>
       *
       * <pre>
       *time stamp
       * </pre>
       */
      public Builder clearStamp() {
        bitField0_ = (bitField0_ & ~0x00000002);
        stamp_ = 0;
        onChanged();
        return this;
      }

      // required float position_x = 3;
      private float positionX_ ;
      /**
       * <code>required float position_x = 3;</code>
       *
       * <pre>
       *axis x
       * </pre>
       */
      public boolean hasPositionX() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required float position_x = 3;</code>
       *
       * <pre>
       *axis x
       * </pre>
       */
      public float getPositionX() {
        return positionX_;
      }
      /**
       * <code>required float position_x = 3;</code>
       *
       * <pre>
       *axis x
       * </pre>
       */
      public Builder setPositionX(float value) {
        bitField0_ |= 0x00000004;
        positionX_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required float position_x = 3;</code>
       *
       * <pre>
       *axis x
       * </pre>
       */
      public Builder clearPositionX() {
        bitField0_ = (bitField0_ & ~0x00000004);
        positionX_ = 0F;
        onChanged();
        return this;
      }

      // required float position_y = 4;
      private float positionY_ ;
      /**
       * <code>required float position_y = 4;</code>
       *
       * <pre>
       *axis y
       * </pre>
       */
      public boolean hasPositionY() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required float position_y = 4;</code>
       *
       * <pre>
       *axis y
       * </pre>
       */
      public float getPositionY() {
        return positionY_;
      }
      /**
       * <code>required float position_y = 4;</code>
       *
       * <pre>
       *axis y
       * </pre>
       */
      public Builder setPositionY(float value) {
        bitField0_ |= 0x00000008;
        positionY_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required float position_y = 4;</code>
       *
       * <pre>
       *axis y
       * </pre>
       */
      public Builder clearPositionY() {
        bitField0_ = (bitField0_ & ~0x00000008);
        positionY_ = 0F;
        onChanged();
        return this;
      }

      // required uint32 floor_id = 5;
      private int floorId_ ;
      /**
       * <code>required uint32 floor_id = 5;</code>
       *
       * <pre>
       *floorid
       * </pre>
       */
      public boolean hasFloorId() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required uint32 floor_id = 5;</code>
       *
       * <pre>
       *floorid
       * </pre>
       */
      public int getFloorId() {
        return floorId_;
      }
      /**
       * <code>required uint32 floor_id = 5;</code>
       *
       * <pre>
       *floorid
       * </pre>
       */
      public Builder setFloorId(int value) {
        bitField0_ |= 0x00000010;
        floorId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 floor_id = 5;</code>
       *
       * <pre>
       *floorid
       * </pre>
       */
      public Builder clearFloorId() {
        bitField0_ = (bitField0_ & ~0x00000010);
        floorId_ = 0;
        onChanged();
        return this;
      }

      // required uint32 scene = 6;
      private int scene_ ;
      /**
       * <code>required uint32 scene = 6;</code>
       *
       * <pre>
       *scene
       * </pre>
       */
      public boolean hasScene() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>required uint32 scene = 6;</code>
       *
       * <pre>
       *scene
       * </pre>
       */
      public int getScene() {
        return scene_;
      }
      /**
       * <code>required uint32 scene = 6;</code>
       *
       * <pre>
       *scene
       * </pre>
       */
      public Builder setScene(int value) {
        bitField0_ |= 0x00000020;
        scene_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 scene = 6;</code>
       *
       * <pre>
       *scene
       * </pre>
       */
      public Builder clearScene() {
        bitField0_ = (bitField0_ & ~0x00000020);
        scene_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Result)
    }

    static {
      defaultInstance = new Result(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Result)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_Result_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Result_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\013phone.proto\"s\n\006Result\022\021\n\tphone_mac\030\001 \002" +
      "(\t\022\r\n\005stamp\030\002 \002(\r\022\022\n\nposition_x\030\003 \002(\002\022\022\n" +
      "\nposition_y\030\004 \002(\002\022\020\n\010floor_id\030\005 \002(\r\022\r\n\005s" +
      "cene\030\006 \002(\r"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_Result_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_Result_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_Result_descriptor,
              new String[] { "PhoneMac", "Stamp", "PositionX", "PositionY", "FloorId", "Scene", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
