import {Routes} from "../common/Routes";
import {Client} from "../domain/Client";
import {User} from "../domain/Users";

export const getClients = async (): Promise<Client[]> => {
    const data = await fetch(Routes.clients.Get);
    return (await data.json()) as Client[];
};

export const getClient = async (id: string): Promise<Client> => {
    const data = await fetch(Routes.clients.Get + "/" + id);
    return (await data.json()) as Client;
};

export const getUsers = async (): Promise<User[]> => {
    const data = await fetch(Routes.users.Get);
    return (await data.json()) as User[];
};