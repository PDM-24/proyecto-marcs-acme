const authenticate = (req, res, next) => {
    // Lógica de autenticación aquí
    next();
};

module.exports = authenticate;
