package me.jlblog.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.jlblog.app.domain.Samples;

@Mapper
public interface SampleMapper {
	List<Samples> findAllSample();
}
