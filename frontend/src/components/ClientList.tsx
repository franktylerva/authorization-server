import AppNavbar from "./AppNavBar";
import {Container} from "reactstrap";
import {useState,useEffect} from "react";
import {Client} from "../domain/Client";
import {getClients} from "../clients/AuthServerClient";

const ClientList = () => {
    const [clients, setClients] = useState<Client[]>();

    useEffect(() => {
        const call = async () => {
            let response = await getClients();
            setClients(response);
        };
        call();
    }, []);

    return (
        <>
            <AppNavbar/>
            <Container fluid>
                <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">Client Id</th>
                        <th scope="col">Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    {clients?.map((client :Client) => (
                        <tr>
                            <td>{client.clientId}</td>
                            <td>{client.clientName}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </Container>
        </>
    )
}

export default ClientList;