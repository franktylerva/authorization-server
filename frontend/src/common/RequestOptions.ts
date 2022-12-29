export const getRequestOptions = (method: string, bodyObject: any): any => {
    const requestOptions = {
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "X-XSRF-TOKEN": document.cookie
                .split("; ")
                .filter((row) => row.startsWith("XSRF-TOKEN="))
                .map((c) => c.split("=")[1])[0],
        },
        method: method,
        body: JSON.stringify(bodyObject),
    };
    return requestOptions;
};