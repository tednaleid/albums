

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Album List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Album</g:link></span>
        </div>
        <div class="body">
            <h1>Album List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Artist</th>
                   	    
                   	        <g:sortableColumn property="title" title="Title" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${albumInstanceList}" status="i" var="albumInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" params="[artistName: albumInstance.artist.name.encodeAsArtistName(), albumTitle: albumInstance.title.encodeAsAlbumTitle()]">${fieldValue(bean:albumInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:albumInstance, field:'artist')}</td>
                        
                            <td>${fieldValue(bean:albumInstance, field:'title')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Album.count()}" />
            </div>
        </div>
    </body>
</html>
