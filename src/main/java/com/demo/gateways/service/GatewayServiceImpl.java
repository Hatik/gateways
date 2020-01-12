package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.entity.PeripheralDevice;
import com.demo.gateways.exception.IpV4AddressFormatException;
import com.demo.gateways.exception.SerialNumberUniquenessException;
import com.demo.gateways.repository.GatewayRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatewayServiceImpl implements GatewayService {
    @Autowired
    GatewayRepository gatewayRepo;

    @Override
    public Gateway save(Gateway gateway) throws IpV4AddressFormatException, SerialNumberUniquenessException {
        ipV4Check(gateway.getIpV4Address());
        serialNumberCheck(gateway.getSerialNumber(), gateway.getId());
        return gatewayRepo.save(gateway);
    }

    private void ipV4Check(String address) throws IpV4AddressFormatException {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        if (!validator.isValid(address))throw new IpV4AddressFormatException("Wrong Ipv4 Address");
    }
    private void serialNumberCheck(String serialNumber, Long id) throws SerialNumberUniquenessException {
        List<Gateway> list = gatewayRepo.findAllBySerialNumber(serialNumber, id);
        if (list.size() > 0) throw new SerialNumberUniquenessException();
    }

    @Override
    public List<Gateway> getAll() {
        return gatewayRepo.findAll();
    }

    @Override
    public Optional<Gateway> getById(Long id) {
        return gatewayRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        gatewayRepo.deleteById(id);
    }

}
