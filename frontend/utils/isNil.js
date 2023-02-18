/**
 * True if obj is undefined, null, empty object, empty string or empty list
 * @param {*} obj object
 * @returns Boolean is Nil.
 */
export function isNil(obj) {
    if (obj === undefined || obj === null || obj === {} || obj === "" || obj.length === 0) return true;
    else return false;
}