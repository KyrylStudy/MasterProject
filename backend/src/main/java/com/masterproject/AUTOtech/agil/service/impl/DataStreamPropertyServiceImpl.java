package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.DataStreamPropertyDto;
import com.masterproject.AUTOtech.agil.entity.DataStream;
import com.masterproject.AUTOtech.agil.entity.DataStreamProperty;
import com.masterproject.AUTOtech.agil.exceprions.DataStreamNotFound;
import com.masterproject.AUTOtech.agil.exceprions.DataStreamPropertyNotFound;
import com.masterproject.AUTOtech.agil.mapper.DataStreamPropertyMapper;
import com.masterproject.AUTOtech.agil.repository.DataStreamPropertyRepository;
import com.masterproject.AUTOtech.agil.repository.DataStreamRepository;
import com.masterproject.AUTOtech.agil.service.DataStreamPropertyService;

@Service
public class DataStreamPropertyServiceImpl implements DataStreamPropertyService {

    private DataStreamPropertyRepository dataStreamPropertyRepository;
    private DataStreamRepository dataStreamRepository;

    public DataStreamPropertyServiceImpl(DataStreamPropertyRepository dataStreamPropertyRepository, DataStreamRepository dataStreamRepository) {
        this.dataStreamPropertyRepository = dataStreamPropertyRepository;
        this.dataStreamRepository = dataStreamRepository;
    }

    @Override
    public DataStreamPropertyDto createDataStreamProperty(Long data_stream_id, DataStreamPropertyDto dataStreamPropertyDto) {
    	DataStreamProperty dataStreamProperty = DataStreamPropertyMapper.mapToDataStreamProperty(dataStreamPropertyDto);

    	DataStream dataStream = dataStreamRepository.findById(data_stream_id).orElseThrow(() -> new DataStreamNotFound("Data Stream with associated Bus Property could not be found!"));
        
    	dataStreamProperty.setDataStream(dataStream);

        DataStreamProperty newConnectionProperty = dataStreamPropertyRepository.save(dataStreamProperty);

        return DataStreamPropertyMapper.mapToDataStreamPropertyDto(newConnectionProperty);
    }

   /* @Override
    public EcuSoftwareDto getEcuSoftwareDtoById(Long id) {
        EcuSoftware ecuSoftware = ecuSoftwareRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("EcuSoftware does not exist"));
        return EcuSoftwareMapper.mapToEcuSoftwareDto(ecuSoftware);
    }*/

	@Override
	public List<DataStreamPropertyDto> getAllDataStreamPropertyByDataStreamId(Long data_stream_id) {
		List<DataStreamProperty> allDataStreamProperties = dataStreamPropertyRepository.findByDataStreamId(data_stream_id);
		return allDataStreamProperties.stream().map((dataStreamProperty) -> DataStreamPropertyMapper.mapToDataStreamPropertyDto(dataStreamProperty)).collect(Collectors.toList());

	}

	@Override
	public DataStreamPropertyDto updateDataStreamProperty(DataStreamPropertyDto dataStreamPropertyDto, Long id) {
		DataStreamProperty dataStreamProperty = dataStreamPropertyRepository
				.findById(id)
				.orElseThrow(()->new DataStreamPropertyNotFound("Data Stream Property could not be updated!"));
		
		dataStreamProperty.setName(dataStreamPropertyDto.getName());
		dataStreamProperty.setValue(dataStreamPropertyDto.getValue());
	
		
		DataStreamProperty updatedDataStreamProperty = dataStreamPropertyRepository.save(dataStreamProperty); 	
		return DataStreamPropertyMapper.mapToDataStreamPropertyDto(updatedDataStreamProperty);
	}

	@Override
	public void deleteDataStreamPropertyId(Long id) {
		DataStreamProperty dataStreamProperty = dataStreamPropertyRepository
				.findById(id)
				.orElseThrow(()->new DataStreamPropertyNotFound("Data Stream Property could not be deleted!"));
		dataStreamPropertyRepository.delete(dataStreamProperty);
	}
	
}

