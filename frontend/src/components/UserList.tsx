import AppNavbar from "./AppNavBar";
import {Container} from "reactstrap";
import {Client} from "../domain/Client";
import {getClients, getUsers} from "../clients/AuthServerClient";
import {useEffect, useState} from "react";
import {User} from "../domain/Users";


const UserList = () => {
    const [users, setUsers] = useState<User[]>();

    useEffect(() => {
        const call = async () => {
            let response = await getUsers();
            setUsers(response);
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
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Enabled</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users?.map((user :User) => (
                        <tr>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.enabled}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </Container>
        </>
    )
}

export default UserList;