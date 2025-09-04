import Cookies from "js-cookie";

export function setCookie(name: string, value: string) {
    Cookies.set(name, value, { expires: 0.02083 }); // Cookie expires in 30 mins. (30/1440 min per day)
}

export function getCookie(name: string) {
    return Cookies.get(name);
}

export function deleteCookie(name: string) {
    Cookies.remove(name);
}