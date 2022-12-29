import React, { useState } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

const AppNavbar = () => {

    const [isOpen, setIsOpen] = useState(false);

    return (
        <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Spring Authorization Server</NavbarBrand>
            <NavbarToggler onClick={() => { setIsOpen(!isOpen) }}/>
            <Collapse isOpen={isOpen} navbar>
                <Nav>
                    <NavItem>
                        <NavLink href="/">Home</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="/users">Users</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="/clients">Clients</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>
    );
};

export default AppNavbar;