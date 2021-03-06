class ArtistController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ artistInstanceList: Artist.list( params ) ]
    }

    def show = {
        def artistName = params.artistName.decodeArtistName()
        def artistInstance = Artist.findByName( artistName )

        if(!artistInstance) {
            flash.message = "Artist not found with name ${artistName}"
            redirect(action:list)
        }
        else { return [ artistInstance : artistInstance ] }
    }

    def delete = {
        def artistInstance = Artist.get( params.id )
        if(artistInstance) {
            def artistName = artistInstance.name
            artistInstance.delete()
            flash.message = "Artist ${artistName} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Artist not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def artistInstance = Artist.get( params.id )

        if(!artistInstance) {
            flash.message = "Artist not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ artistInstance : artistInstance ]
        }
    }

    def update = {
        def artistInstance = Artist.get( params.id )
        if(artistInstance) {
            artistInstance.properties = params
            if(!artistInstance.hasErrors() && artistInstance.save()) {
                flash.message = "Artist ${params.id} updated"
                redirect(action:show, params:[artistName: artistInstance.name.encodeAsArtistName()])
            }
            else {
                render(view:'edit',model:[artistInstance:artistInstance])
            }
        }
        else {
            flash.message = "Artist not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def artistInstance = new Artist()
        artistInstance.properties = params
        return ['artistInstance':artistInstance]
    }

    def save = {
        def artistInstance = new Artist(params)
        if(!artistInstance.hasErrors() && artistInstance.save()) {
            flash.message = "Artist ${artistInstance.id} created"
            redirect(action:show, params:[artistName: artistInstance.name.encodeAsArtistName()])
        }
        else {
            render(view:'create',model:[artistInstance:artistInstance])
        }
    }
}
