package am2.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import am2.ArsMagica2;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class AMDataWriter{
	ByteArrayOutputStream bytes;
	DataOutputStream data;

	public AMDataWriter(){
		bytes = new ByteArrayOutputStream();
		data = new DataOutputStream(bytes);
	}

	public AMDataWriter add(int value){
		try{
			data.writeInt(Integer.valueOf(value));
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(boolean value){
		try{
			data.writeBoolean(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error(e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(byte value){
		try{
			data.writeByte(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(String value){
		try{
			data.writeUTF(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(short value){
		try{
			data.writeShort(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(double value){
		try{
			data.writeDouble(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(float value){
		try{
			data.writeFloat(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(long value){
		try{
			data.writeLong(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(byte[] value){
		try{
			data.write(value);
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(int[] value){
		try{
			data.writeInt(Integer.valueOf(value.length));
			for (int i = 0; i < value.length; ++i)
				data.writeInt(Integer.valueOf(value[i]));
		}catch (IOException e){
			ArsMagica2.LOGGER.error("AMDataWriter: " + e.getMessage());
		}
		return this;
	}

	public AMDataWriter add(NBTTagCompound compound){
		try{
			ByteBuf buf = Unpooled.buffer();
			ByteBufUtils.writeTag(buf, compound);
			byte[] arr = buf.array();
			data.writeInt(arr.length);
			data.write(arr);
		}catch (IOException ex){
			ArsMagica2.LOGGER.error("AMDataWriter: " + ex.getMessage());
		}
		return this;
	}

	public AMDataWriter add(ItemStack stack){
		NBTTagCompound compound = new NBTTagCompound();
		stack.writeToNBT(compound);
		return add(compound);
	}

	public byte[] generate(){
		return bytes.toByteArray();
	}
}