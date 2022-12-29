import React, {useEffect, useState, ChangeEventHandler} from "react";
import {Client} from "../domain/Client";
import Form from 'react-bootstrap/Form';
import {FormControlElement} from 'react-bootstrap/FormControl'
import Button from 'react-bootstrap/Button';
import AppNavbar from "./AppNavBar";
import {Card, Container} from "react-bootstrap";
import {getClient} from "../clients/AuthServerClient";

const ClientList = () => {

    const [client, setClient] = useState<Client>();
    const [authorizationGrantTypes, setAuthorizationGrantTypes] = useState<string[]>(["refresh_token","client_credentials","authorization_code", "x"]);
    const [clientAuthenticationMethods, setClientAuthenticationMethods] = useState<string[]>(["client_secret_basic","Z"]);

    useEffect(() => {
        const call = async () => {
            let response = await getClient("23199d23-7366-493f-a39c-78d988c43df8");
            setClient(response);
        };
        call();
    }, []);


    const handleInputChange = (e: React.FormEvent<HTMLInputElement>) => {
        const update = {[e.currentTarget.id]: e.currentTarget.value};
        setClient({...client, ...update} as Client);
    }

    const handleCheckChange: ChangeEventHandler<FormControlElement> = (e: React.FormEvent<FormControlElement>) => {

        // @ts-ignore
        const updatedList: Set<string> = new Set<string>(client[e.currentTarget.id]);


        if (e.currentTarget.checked) {
            updatedList.add(e.currentTarget.value);
        } else {
            updatedList.delete(e.currentTarget.value);
        }

        const update = {[e.currentTarget.id]: Array.from(updatedList)};
        setClient({...client, ...update} as Client);

    }

    return (
        <>
            <AppNavbar/>
            <Container fluid>
                <Form>
                    <Card style={{ width: '70rem' }}>
                        <Card.Header>OAuth Client Configuration</Card.Header>
                        <Card.Body>
                            <div className="row">
                                <div className="form-row col-5">

                                    <Form.FloatingLabel label="Client Id" controlId="clientId">
                                        <Form.Control type="email" placeholder="Enter Client Id" value={client?.clientId} onChange={handleInputChange}/>
                                    </Form.FloatingLabel>

                                    <Form.FloatingLabel label="Client Name" controlId="clientName">
                                        <Form.Control type="text" placeholder="Enter Client Name" value={client?.clientName} onChange={handleInputChange}/>
                                    </Form.FloatingLabel>

                                    <Form.FloatingLabel label="Client Secret" controlId="clientSecret">
                                        <Form.Control type="password" placeholder="Client Secret" value={client?.clientSecret} onChange={handleInputChange}/>
                                    </Form.FloatingLabel>

                                    <Form.FloatingLabel label="Issued" controlId="clientIdIssuedAt">
                                        <Form.Control type="datetime" disabled={true} value={client?.clientIdIssuedAt} onChange={handleInputChange}/>
                                    </Form.FloatingLabel>

                                    <Form.FloatingLabel label="Expiration" controlId="clientSecretExpiresAt">
                                        <Form.Control type="datetime" disabled={true} value={client?.clientSecretExpiresAt} onChange={handleInputChange}/>
                                    </Form.FloatingLabel>

                                </div>
                                <div className="form-row col-2">

                                    <Form.Group className="mb-3" controlId="authorizationGrantTypes">
                                        <Form.Label>Grant Types</Form.Label>
                                        {authorizationGrantTypes?.map((value :string, index) => (
                                            <Form.Check type="checkbox" checked={client?.authorizationGrantTypes.includes(value)} key={index} value={value} label={value} onChange={handleCheckChange} />
                                        ))}
                                    </Form.Group>
                                </div>
                                <div className="form-row col-3">
                                    <Form.Group className="mb-3" controlId="clientAuthenticationMethods">
                                        <Form.Label>Authentication Methods</Form.Label>
                                        {clientAuthenticationMethods?.map((value :string, index) => (
                                            <Form.Check type="checkbox" checked={client?.clientAuthenticationMethods.includes(value)} key={index} value={value} label={value} onChange={handleCheckChange} />
                                        ))}
                                    </Form.Group>
                                </div>
                            </div>
                            <div className="row">

                            </div>

                        </Card.Body>
                        <Card.Footer>
                            <Button variant="primary" type="submit" className="col-1 client-submit">Submit</Button>
                        </Card.Footer>
                    </Card>
                </Form>
                <br/>
                <div><pre>{JSON.stringify(client, null, 2) }</pre></div>
            </Container>
        </>
    )




}

export default ClientList;