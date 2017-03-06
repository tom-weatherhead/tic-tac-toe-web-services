/**
 * SOAPTicTacToeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.thaw.webservices.soaptictactoe;

public class SOAPTicTacToeServiceLocator extends org.apache.axis.client.Service implements org.thaw.webservices.soaptictactoe.SOAPTicTacToeService {

    public SOAPTicTacToeServiceLocator() {
    }


    public SOAPTicTacToeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SOAPTicTacToeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SOAPTicTacToe
    private java.lang.String SOAPTicTacToe_address = "http://localhost:8081/SOAPTicTacToe/services/SOAPTicTacToe";

    public java.lang.String getSOAPTicTacToeAddress() {
        return SOAPTicTacToe_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAPTicTacToeWSDDServiceName = "SOAPTicTacToe";

    public java.lang.String getSOAPTicTacToeWSDDServiceName() {
        return SOAPTicTacToeWSDDServiceName;
    }

    public void setSOAPTicTacToeWSDDServiceName(java.lang.String name) {
        SOAPTicTacToeWSDDServiceName = name;
    }

    public org.thaw.webservices.soaptictactoe.SOAPTicTacToe getSOAPTicTacToe() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAPTicTacToe_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAPTicTacToe(endpoint);
    }

    public org.thaw.webservices.soaptictactoe.SOAPTicTacToe getSOAPTicTacToe(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.thaw.webservices.soaptictactoe.SOAPTicTacToeSoapBindingStub _stub = new org.thaw.webservices.soaptictactoe.SOAPTicTacToeSoapBindingStub(portAddress, this);
            _stub.setPortName(getSOAPTicTacToeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSOAPTicTacToeEndpointAddress(java.lang.String address) {
        SOAPTicTacToe_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.thaw.webservices.soaptictactoe.SOAPTicTacToe.class.isAssignableFrom(serviceEndpointInterface)) {
                org.thaw.webservices.soaptictactoe.SOAPTicTacToeSoapBindingStub _stub = new org.thaw.webservices.soaptictactoe.SOAPTicTacToeSoapBindingStub(new java.net.URL(SOAPTicTacToe_address), this);
                _stub.setPortName(getSOAPTicTacToeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SOAPTicTacToe".equals(inputPortName)) {
            return getSOAPTicTacToe();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soaptictactoe.webservices.thaw.org", "SOAPTicTacToeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soaptictactoe.webservices.thaw.org", "SOAPTicTacToe"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SOAPTicTacToe".equals(portName)) {
            setSOAPTicTacToeEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
