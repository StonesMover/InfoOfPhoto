package com.zzx.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class PictureUtil {
	public HashMap<String, Object> readPic(String path) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Tag tag = null;
		File jpegFile = new File(path);
		Metadata metadata;
		try {
			metadata = JpegMetadataReader.readMetadata(jpegFile);
			Iterator<Directory> it = metadata.getDirectories().iterator();
			while (it.hasNext()) {
				Directory exif = it.next();
				Iterator<Tag> tags = exif.getTags().iterator();
				while (tags.hasNext()) {
					tag = (Tag) tags.next();
					System.out.println(tag.getTagName() + "--" + tag.getDescription());
					map.put(tag.getTagName(), tag.getDescription());
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
